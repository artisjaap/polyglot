package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.action.to.BriefPreviewConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.BriefMetaDataset;
import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.brieflocatie.BriefLocatieType;
import be.artisjaap.document.api.brieflocatie.BriefOpslagLocatie;
import be.artisjaap.document.assembler.BriefAssembler;
import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.BriefLocatie;
import be.artisjaap.document.model.GegenereerdeBrief;
import be.artisjaap.document.model.mongo.BriefRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.utils.PDFUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenereerBrief {

    @Value("${lid.bestanden.directory:c:/temp/}")
    private String basePath;

    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private GenereerGecombineerdeTemplate genereerGecombineerdeTemplate;

    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;

    @Autowired
    private BriefAssembler briefAssembler;

    public Optional<byte[]> voor(BriefConfigTO briefConfig){
        Brief brief = briefRepository.findByCodeAndTaalAndActief(briefConfig.getCode(), briefConfig.getTaal()).orElseThrow(() -> new IllegalStateException("brief niet gevonden"));
        BriefConfigTO briefConfigMetExtraDatasets = extendedBriefconfig(briefConfig, brief);
        List<TemplateDataTO> templateData = brief.getPaginas().stream().map(p -> genereerGecombineerdeTemplate.genereer(p, briefConfigMetExtraDatasets)).collect(Collectors.toList());
        List<byte[]> collect = templateData.stream().map(TemplateDataTO::getData).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Optional<byte[]> bytes = PDFUtils.mergePdfs(collect);


        if(!briefConfig.dryRun()) {
            Map<String, Object> briefDatasetData = new HashMap<>();
            if (briefConfig.getOpslagSettingsTO().briefLocatieType() != BriefLocatieType.NIET_OPSLAAN) {
                for (String dataset : briefConfigMetExtraDatasets.datasets()) {
                    DatasetConfig datasetConfig = briefConfigMetExtraDatasets.forDataset(dataset);
                    Object data = datasetConfig.data();

                    String datasetNaam = datasetConfig.metaName();
                    if (!briefConfigMetExtraDatasets.isBlacklisted(datasetNaam)) {
                        briefDatasetData.put(datasetNaam, data);
                    }
                }
            }

            Set<ObjectId> gebruikteTemplates = templateData.stream().flatMap(d -> d.getGebruikteTemplates().stream()).collect(Collectors.toSet());
            BriefLocatie briefLocatie = bewaarBrief(briefConfigMetExtraDatasets, bytes.get());

            GegenereerdeBrief gegenereerdeBrief = GegenereerdeBrief.newBuilder()
                    .withBriefRef(brief.getId())
                    .withDatasets(briefDatasetData)
                    .withBriefLocatie(briefLocatie) //resolve bytes voor brief, in db op filesystem, ...
                    .withGebruikteTemplates(gebruikteTemplates)
                    .withTaal(briefConfigMetExtraDatasets.getTaal())
                    .withBriefcode(briefConfigMetExtraDatasets.getCode())
                    .build();
            gegenereerdeBriefRepository.save(gegenereerdeBrief);
        }
        return bytes;

    }


    private BriefLocatie bewaarBrief(BriefConfigTO briefConfig, byte[] doc) {
        BriefOpslagLocatie opslagSettingsTO = briefConfig.getOpslagSettingsTO();
        BriefLocatie.Builder briefLocatieBuilder = BriefLocatie.newBuilder();
        String filename = briefConfig.getBestandsnaam().filename(briefConfig.getDatasetProvider());
        briefLocatieBuilder.withGegenereerdeBestandsnaam(filename);
        switch (opslagSettingsTO.briefLocatieType()){
            case IN_DB:
                briefLocatieBuilder.withType(BriefLocatieType.IN_DB).withDocument(doc);
                break;
            case ABSOLUTE_PATH:
                PDFUtils.savePdf(opslagSettingsTO.path(), filename, doc);
                briefLocatieBuilder.withType(BriefLocatieType.ABSOLUTE_PATH).withPath(opslagSettingsTO.path());
                break;
            case RELATIVE_PATH:
                String path = basePath + opslagSettingsTO.path();
                PDFUtils.savePdf(path, filename,  doc);
                briefLocatieBuilder.withType(BriefLocatieType.RELATIVE_PATH).withPath(path);
                break;
            case NIET_OPSLAAN:
                break;
        }
        return briefLocatieBuilder.build();
    }

    private BriefConfigTO extendedBriefconfig(BriefConfigTO briefConfig, Brief brief){
        DatasetProviderImpl datasetProvider = DatasetProviderImpl.create();
        briefConfig.getDatasetProvider().datasetNames().forEach(d -> datasetProvider.add(d, briefConfig.getDatasetProvider().get(d).data(),briefConfig.getDatasetProvider().get(d).isAsList() ));
        datasetProvider.add("BriefMeta", BriefMetaDataset.fromBrief(briefAssembler.assembleTO(brief)));

        return BriefConfigTO.newBuilder(briefConfig)
                .withDatasetProvider(datasetProvider)
                .build();
    }

    public TemplateDataTO voorBriefMetId(ObjectId briefId, BriefPreviewConfigTO briefConfigTO) {
        Brief brief = briefRepository.findById(briefId).orElseThrow(() -> new UnsupportedOperationException("Brief bestaat niet"));

        BriefConfigTO briefConfig = BriefConfigTO.newBuilder()
                .withDatasetProvider(briefConfigTO)
                .withTaal(brief.getTaal())
                .build();

        List<TemplateDataTO> templateData = brief.getPaginas().stream().map(p -> genereerGecombineerdeTemplate.genereer(p, briefConfig)).collect(Collectors.toList());
        List<byte[]> collect = templateData.stream().map(TemplateDataTO::getData).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Optional<byte[]> bytes = PDFUtils.mergePdfs(collect);

        Set<ObjectId> gebruikteTemplates = templateData.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        return TemplateDataTO.newBuilder()
                .withData(bytes)
                .withGebruikteTemplates(gebruikteTemplates)
                .build();

    }
}

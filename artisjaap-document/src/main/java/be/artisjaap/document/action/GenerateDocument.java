package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.BriefMetaDataset;
import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.brieflocatie.BriefLocatieType;
import be.artisjaap.document.api.brieflocatie.BriefOpslagLocatie;
import be.artisjaap.document.assembler.DocumentAssembler;
import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.DocumentLocation;
import be.artisjaap.document.model.GegenereerdeBrief;
import be.artisjaap.document.model.mongo.DocumentRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.utils.PDFUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenerateDocument {

    @Value("${lid.bestanden.directory:c:/temp/}")
    private String basePath;

    private final DocumentRepository documentRepository;
    private final GenerateCombinedTemplate generateCombinedTemplate;
    private final GegenereerdeBriefRepository gegenereerdeBriefRepository;
    private final DocumentAssembler documentAssembler;

    public GenerateDocument(DocumentRepository documentRepository, GenerateCombinedTemplate generateCombinedTemplate, GegenereerdeBriefRepository gegenereerdeBriefRepository, DocumentAssembler documentAssembler) {
        this.documentRepository = documentRepository;
        this.generateCombinedTemplate = generateCombinedTemplate;
        this.gegenereerdeBriefRepository = gegenereerdeBriefRepository;
        this.documentAssembler = documentAssembler;
    }

    public Optional<byte[]> forDocument(BriefConfigTO briefConfig) {
        Document document = documentRepository.findByCodeAndTaalAndActief(briefConfig.getCode(), briefConfig.getTaal()).orElseThrow(() -> new IllegalStateException("document niet gevonden"));
        BriefConfigTO briefConfigMetExtraDatasets = extendedDocumentConfig(briefConfig, document);
        List<TemplateDataTO> templateData = document.getPages().stream().map(p -> generateCombinedTemplate.generate(p, briefConfigMetExtraDatasets)).collect(Collectors.toList());
        List<byte[]> collect = templateData.stream().map(TemplateDataTO::getData)
                .map(Optional::ofNullable)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Optional<byte[]> bytes = PDFUtils.mergePdfs(collect);


        if (!briefConfig.isDryRun()) {
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

            Set<ObjectId> usedTemplates = templateData.stream().flatMap(d -> d.getGebruikteTemplates().stream()).collect(Collectors.toSet());
            DocumentLocation documentLocation = saveDocument(briefConfigMetExtraDatasets, bytes.get());

            GegenereerdeBrief gegenereerdeBrief = GegenereerdeBrief.newBuilder()
                    .withBriefRef(document.getId())
                    .withDatasets(briefDatasetData)
                    .withBriefLocatie(documentLocation) //resolve bytes forTemplateId document, in db op filesystem, ...
                    .withGebruikteTemplates(usedTemplates)
                    .withTaal(briefConfigMetExtraDatasets.getTaal())
                    .withBriefcode(briefConfigMetExtraDatasets.getCode())
                    .build();
            gegenereerdeBriefRepository.save(gegenereerdeBrief);
        }
        return bytes;

    }


    private DocumentLocation saveDocument(BriefConfigTO briefConfig, byte[] doc) {
        BriefOpslagLocatie opslagSettingsTO = briefConfig.getOpslagSettingsTO();
        DocumentLocation.Builder briefLocatieBuilder = DocumentLocation.newBuilder();
        String filename = briefConfig.getBestandsnaam().filename(briefConfig.getDatasetProvider());
        briefLocatieBuilder.withGegenereerdeBestandsnaam(filename);
        switch (opslagSettingsTO.briefLocatieType()) {
            case IN_DB:
                briefLocatieBuilder.withType(BriefLocatieType.IN_DB).withDocument(doc);
                break;
            case ABSOLUTE_PATH:
                PDFUtils.savePdf(opslagSettingsTO.path(), filename, doc);
                briefLocatieBuilder.withType(BriefLocatieType.ABSOLUTE_PATH).withPath(opslagSettingsTO.path());
                break;
            case RELATIVE_PATH:
                String path = basePath + opslagSettingsTO.path();
                PDFUtils.savePdf(path, filename, doc);
                briefLocatieBuilder.withType(BriefLocatieType.RELATIVE_PATH).withPath(path);
                break;
            case NIET_OPSLAAN:
                break;
        }
        return briefLocatieBuilder.build();
    }

    private BriefConfigTO extendedDocumentConfig(BriefConfigTO briefConfig, Document document) {
        DatasetProviderImpl datasetProvider = DatasetProviderImpl.create();
        briefConfig.getDatasetProvider().datasetNames().forEach(d -> datasetProvider.add(d, briefConfig.getDatasetProvider().get(d).data(), briefConfig.getDatasetProvider().get(d).metaClass(), briefConfig.getDatasetProvider().get(d).isAsList()));
        datasetProvider.add("BriefMeta", BriefMetaDataset.fromBrief(documentAssembler.assembleTO(document)));

        return briefConfig.toBuilder()
                .datasetProvider(datasetProvider)
                .build();
    }

    public TemplateDataTO voorBriefMetId(String briefId, DatasetProvider briefConfigTO) {
        Document document = documentRepository.findById(new ObjectId(briefId)).orElseThrow(() -> new UnsupportedOperationException("Document bestaat niet"));

        BriefConfigTO briefConfig = BriefConfigTO.builder()
                .datasetProvider(briefConfigTO)
                .taal(document.getTaal())
                .build();

        List<TemplateDataTO> templateData = document.getPages().stream().map(p -> generateCombinedTemplate.generate(p, briefConfig)).collect(Collectors.toList());
        List<byte[]> collect = templateData.stream().map(TemplateDataTO::getData)
                .map(Optional::ofNullable)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Optional<byte[]> bytes = PDFUtils.mergePdfs(collect);

        Set<ObjectId> gebruikteTemplates = templateData.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        return TemplateDataTO.builder()
                .data(bytes.orElse(null))
                .gebruikteTemplates(gebruikteTemplates)
                .build();

    }
}

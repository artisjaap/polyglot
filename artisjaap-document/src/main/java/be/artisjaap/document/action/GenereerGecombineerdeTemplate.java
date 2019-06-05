package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.Pagina;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.utils.PDFUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenereerGecombineerdeTemplate {
    @Autowired
    private GenereerTemplate genereerTemplate;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    @Autowired
    private ZoekBeschikbareEenvoudigeTemplates zoekBeschikbareEenvoudigeTemplates;


    public TemplateDataTO genereer(Pagina pagina, BriefConfigTO briefConfig) {

        switch (pagina.getPaginaType()) {
            case GECOMBINEERD:
                return genereerGecombineerdePagina(pagina, briefConfig);
            case TEMPLATE:
                return genereerTemplate.voor(pagina.getCode(), briefConfig);
        }
        return TemplateDataTO.empty();
    }

    private TemplateDataTO genereerGecombineerdePagina(Pagina pagina, BriefConfigTO briefConfig) {
        GecombineerdeTemplate template = gecombineerdeTemplateRepository.findByCodeAndTaalAndActief(pagina.getCode(), briefConfig.getTaal()).orElseThrow(() -> new IllegalStateException("template niet gevonden"));
        List<TemplateDataTO> collect = template.getTemplates().stream().map(t -> genereerTemplate.voor(t, briefConfig)).collect(Collectors.toList());
        List<byte[]> docs = collect.stream().map(TemplateDataTO::getData).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Set<ObjectId> gebruikteTemplates = collect.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        byte[] gecombineerd = PDFUtils.stampDoc(docs);

        return TemplateDataTO.newBuilder()
                .withData(Optional.of(gecombineerd))
                .withGebruikteTemplates(gebruikteTemplates)
                .build();

    }

    public TemplateDataTO voorGecombineerdeTemplateMetId(ObjectId templateId, DatasetConfigProvider to) {
        GecombineerdeTemplate template = gecombineerdeTemplateRepository.findById(templateId).orElseThrow(() -> new UnsupportedOperationException("Gecombineerde Template bestaat niet"));
        List<TemplateDataTO> collect = template.getTemplates().stream()
                .map(t -> zoekBeschikbareEenvoudigeTemplates.metCodeInTaalActief(t, template.getTaal()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(t -> genereerTemplate.voorTemplateMetId(new ObjectId(t.getId()), to)).collect(Collectors.toList());

        List<byte[]> docs = collect.stream().map(TemplateDataTO::getData).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Set<ObjectId> gebruikteTemplates = collect.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        byte[] gecombineerd = PDFUtils.stampDoc(docs);

        return TemplateDataTO.newBuilder()
                .withData(Optional.of(gecombineerd))
                .withGebruikteTemplates(gebruikteTemplates)
                .build();


    }
}

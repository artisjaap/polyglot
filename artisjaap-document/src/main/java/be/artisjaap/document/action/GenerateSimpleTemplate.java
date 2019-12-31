package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.TemplateRepository;
import be.artisjaap.document.utils.XdocUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class GenerateSimpleTemplate {

    private final TemplateRepository templateRepository;

    public GenerateSimpleTemplate(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public TemplateDataTO voor(String templateCode, BriefConfigTO to) {
        Template template = templateRepository.findByCodeAndTaalAndActief(templateCode, to.getTaal()).orElseThrow(() -> new UnsupportedOperationException("template niet gevonden: " + templateCode));
        return maakTemplateTO(template, to);
    }

    private TemplateDataTO maakTemplateTO(Template template, DatasetConfigProvider to) {
        return TemplateDataTO.builder()
                .data(XdocUtils.maakDocument(template, to).orElse(null))
                .gebruikteTemplates(Collections.singleton(template.getId()))
                .code(template.getCode())
                .build();
    }


    public TemplateDataTO voorTemplateMetId(ObjectId templateId, DatasetConfigProvider to) {
        Optional<Template> template = templateRepository.findById(templateId);
        return template.map(t -> maakTemplateTO(t, to)).orElseThrow(() -> new IllegalArgumentException("template niet gevonden"));
    }
}

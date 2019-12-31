package be.artisjaap.document.action;

import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivateSimpleOrCombinedTemplate {

    private final TemplateRepository templateRepository;
    private final CombinedTemplateRepository combinedTemplateRepository;
    private final DeactivateTemplate deactivateTemplate;

    public ActivateSimpleOrCombinedTemplate(TemplateRepository templateRepository, CombinedTemplateRepository combinedTemplateRepository, DeactivateTemplate deactivateTemplate) {
        this.templateRepository = templateRepository;
        this.combinedTemplateRepository = combinedTemplateRepository;
        this.deactivateTemplate = deactivateTemplate;
    }

    public void activateTemplate(String id) {
        ObjectId objectId = new ObjectId(id);
        activateSimpleTemplate(objectId);
    }

    private void activateSimpleTemplate(ObjectId objectId) {
        Optional<Template> templateOptional = templateRepository.findById(objectId);
        if (templateOptional.isPresent()) {
            Template template = templateOptional.get();
            deactivateTemplate.forSimpleTemplateWithCodeAndLanguage(template.getCode(), template.getTaal());
            template.activeer();
            templateRepository.save(template);
        } else {
            activateCombinedTemplate(objectId);
        }
    }

    private void activateCombinedTemplate(ObjectId objectId) {
        Optional<CombinedTemplate> templateGecombineerdOptional = combinedTemplateRepository.findById(objectId);
        if (templateGecombineerdOptional.isPresent()) {
            CombinedTemplate templateGecombineerd = templateGecombineerdOptional.get();
            deactivateTemplate.forCombinedTemplateWithCodeAndLanguage(templateGecombineerd.getCode(), templateGecombineerd.getTaal());
            templateGecombineerd.activeer();
            combinedTemplateRepository.save(templateGecombineerd);
        } else {
            throw new IllegalStateException("Template met ID niet gevonden");
        }
    }

}

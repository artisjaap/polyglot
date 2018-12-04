package be.artisjaap.document.action;

import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivateTemplate {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    @Autowired
    private DeactivateTemplate deactivateTemplate;

    public void activeerTemplate(String id){
        ObjectId objectId = new ObjectId(id);
        activeerTemplate(objectId);
    }

    private void activeerTemplate(ObjectId objectId) {
        Optional<Template> templateOptional = templateRepository.findById(objectId);
        if(templateOptional.isPresent()){
            Template template = templateOptional.get();
            deactivateTemplate.voorEenvoudigeTemplateMetCodeEnTaal(template.getCode(), template.getTaal());
            template.activeer();
            templateRepository.save(template);
        }else {
            activeerGecombineerdeTemplate(objectId);
        }
    }

    private void activeerGecombineerdeTemplate(ObjectId objectId) {
        Optional<GecombineerdeTemplate> templateGecombineerdOptional = gecombineerdeTemplateRepository.findById(objectId);
        if(templateGecombineerdOptional.isPresent()){
            GecombineerdeTemplate templateGecombineerd= templateGecombineerdOptional.get();
            deactivateTemplate.voorGecombineerdeTemplateMetCodeEnTaal(templateGecombineerd.getCode(), templateGecombineerd.getTaal());
            templateGecombineerd.activeer();
            gecombineerdeTemplateRepository.save(templateGecombineerd);
        }else {
            throw new IllegalStateException("Template met ID niet gevonden");
        }
    }

}

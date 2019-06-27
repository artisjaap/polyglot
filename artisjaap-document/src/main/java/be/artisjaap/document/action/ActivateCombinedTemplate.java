package be.artisjaap.document.action;

import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateCombinedTemplate {


    @Autowired
    private CombinedTemplateRepository combinedTemplateRepository;

    @Autowired
    private DeactivateTemplate deactivateTemplate;

    public void activeerTemplate(String id) {
        ObjectId objectId = new ObjectId(id);
        activeerGecombineerdeTemplate(objectId);
    }


    private void activeerGecombineerdeTemplate(ObjectId objectId) {
        CombinedTemplate templateGecombineerd = combinedTemplateRepository.findById(objectId).orElseThrow(() -> new UnsupportedOperationException("Template bestaat niet"));
        if (templateGecombineerd != null) {
            deactivateTemplate.forCombinedTemplateWithCodeAndLanguage(templateGecombineerd.getCode(), templateGecombineerd.getTaal());
            templateGecombineerd.activeer();
            combinedTemplateRepository.save(templateGecombineerd);
        } else {
            throw new IllegalStateException("Template met ID niet gevonden");
        }
    }
}

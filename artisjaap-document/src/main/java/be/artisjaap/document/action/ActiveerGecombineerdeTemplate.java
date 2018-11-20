package be.artisjaap.document.action;

import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveerGecombineerdeTemplate {


    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    @Autowired
    private DesactiveerTemplate desactiveerTemplate;

    public void activeerTemplate(String id){
        ObjectId objectId = new ObjectId(id);
        activeerGecombineerdeTemplate(objectId);
    }


    private void activeerGecombineerdeTemplate(ObjectId objectId) {
        GecombineerdeTemplate templateGecombineerd = gecombineerdeTemplateRepository.findById(objectId).orElseThrow(() -> new UnsupportedOperationException("Template bestaat niet"));
        if(templateGecombineerd != null){
            desactiveerTemplate.voorGecombineerdeTemplateMetCodeEnTaal(templateGecombineerd.getCode(), templateGecombineerd.getTaal());
            templateGecombineerd.activeer();
            gecombineerdeTemplateRepository.save(templateGecombineerd);
        }else {
            throw new IllegalStateException("Template met ID niet gevonden");
        }
    }
}

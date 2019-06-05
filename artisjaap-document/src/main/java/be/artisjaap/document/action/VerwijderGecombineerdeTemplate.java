package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerwijderGecombineerdeTemplate {

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    public void metId(String id) {
        gecombineerdeTemplateRepository.deleteById(new ObjectId(id));
    }
}

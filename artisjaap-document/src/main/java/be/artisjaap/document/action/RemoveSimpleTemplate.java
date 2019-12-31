package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.TemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveSimpleTemplate {

    private final TemplateRepository templateRepository;

    public RemoveSimpleTemplate(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void metId(String id) {
        templateRepository.deleteById(new ObjectId(id));
    }
}

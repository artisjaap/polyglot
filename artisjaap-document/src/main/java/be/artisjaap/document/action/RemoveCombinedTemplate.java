package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveCombinedTemplate {

    private final CombinedTemplateRepository combinedTemplateRepository;

    public RemoveCombinedTemplate(CombinedTemplateRepository combinedTemplateRepository) {
        this.combinedTemplateRepository = combinedTemplateRepository;
    }

    public void metId(String id) {
        combinedTemplateRepository.deleteById(new ObjectId(id));
    }
}

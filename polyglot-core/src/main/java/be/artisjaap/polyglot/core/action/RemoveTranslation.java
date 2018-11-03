package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveTranslation {

    @Autowired
    private TranslationRepository translationRepository;

    public void withId(String translationId){
        translationRepository.deleteById(new ObjectId(translationId));
    }
}

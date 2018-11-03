package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveLanguagePair {

    @Autowired
    private LanguagePairRepository languagePairRepository;

    public void withId(String languagePairId){
        languagePairRepository.deleteById(new ObjectId(languagePairId));
    }
}

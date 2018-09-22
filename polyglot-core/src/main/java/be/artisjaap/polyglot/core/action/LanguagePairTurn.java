package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LanguagePairTurn {

    @Autowired
    private LanguagePairRepository languagePairRepository;

    public void saveTurnOn(String languagePairId, Boolean reverse){
        LanguagePair languagePair = languagePairRepository.findById(new ObjectId(languagePairId)).orElseThrow(() -> new UnsupportedOperationException("expected language pair to find"));
        if(reverse) {
            languagePair.increaseTurnReverse();
        } else {
            languagePair.increaseTurn();
        }
        languagePairRepository.save(languagePair);
    }
}

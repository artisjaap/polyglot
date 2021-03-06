package be.artisjaap.polyglot.core.action.pairs;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateLanguagePairHealth {

    private final LanguagePairRepository languagePairRepository;

    public UpdateLanguagePairHealth(LanguagePairRepository languagePairRepository) {
        this.languagePairRepository = languagePairRepository;
    }

    public void newWordIntroduced(String languagePairId){
        LanguagePair languagePair = languagePairRepository.findById(new ObjectId(languagePairId)).orElseThrow(() -> new ValidationException(""));
        languagePair.getPracticeHealth().addNewWord();
        languagePairRepository.save(languagePair);
    }


    public void updateCorrect(String languagePairId){
        LanguagePair languagePair = languagePairRepository.findById(new ObjectId(languagePairId)).orElseThrow(() -> new ValidationException(""));
        languagePair.getPracticeHealth().addCorrect();
        languagePairRepository.save(languagePair);
    }

    public void updateIncorrect(String languagePairId){
        LanguagePair languagePair = languagePairRepository.findById(new ObjectId(languagePairId)).orElseThrow(() -> new ValidationException(""));
        languagePair.getPracticeHealth().addIncorrect();
        languagePairRepository.save(languagePair);
    }

}

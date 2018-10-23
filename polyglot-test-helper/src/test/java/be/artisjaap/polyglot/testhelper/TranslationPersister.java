package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslationPersister {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private LanguagePairPersister languagePairPersister;


    public Translation randomTranslationForLanguagePair(LanguagePair languagePair){
        return translationRepository.save(TranslationMother
                .initRandom()
                .withLanguagePairId(languagePair.getId())
                .build())
                ;
    }

    public Translation randomTranslation(){
        return randomTranslationForLanguagePair(languagePairPersister.randomLanguagePair());
    }
}

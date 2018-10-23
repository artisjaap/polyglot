package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TranslationPracticePersister {

    @Autowired
    private UserPersister userPersister;

    @Autowired
    private LanguagePairPersister languagePairPersister;

    @Autowired
    private TranslationPersister translationPersister;

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    public List<TranslationPractice> randomTranslationPracticeInKnowledgeStatus(LanguagePair languagePair, KnowledgeStatus status, Integer numerOfWords) {
        List<TranslationPractice> result = new ArrayList<>();
        for(int i = 0; i < numerOfWords; i++){
            result.add(randomTranslationPracticeInKnowledgeStatus(languagePair, status));
        }
        return result;
    }

    public TranslationPractice randomTranslationPracticeInKnowledgeStatus(LanguagePair languagePair, KnowledgeStatus status) {
        Translation translation = translationPersister.randomTranslationForLanguagePair(languagePair);

        return translationPracticeRepository.save(TranslationPracticeMother.initRandom()
                .withKnowledgeStatus(status)
                .withLanguagePairId(languagePair.getId())
                .withTranslationId(translation.getId())
                .withUserId(languagePair.getUserId())
                .build());

    }

    public TranslationPractice randomTranslationPractice(LanguagePair languagePair) {
        Translation translation = translationPersister.randomTranslationForLanguagePair(languagePair);

        return translationPracticeRepository.save(TranslationPracticeMother.initRandom()
                .withLanguagePairId(languagePair.getId())
                .withTranslationId(translation.getId())
                .withUserId(languagePair.getUserId())
                .build());

    }

    public TranslationPractice randomTranslationPractice(){
        LanguagePair languagePair = languagePairPersister.randomLanguagePair();
        Translation translation = translationPersister.randomTranslationForLanguagePair(languagePair);

        return translationPracticeRepository.save(TranslationPracticeMother.initRandom()
                .withLanguagePairId(languagePair.getId())
                .withTranslationId(translation.getId())
                .withUserId(languagePair.getId())
                .build());
    }
}

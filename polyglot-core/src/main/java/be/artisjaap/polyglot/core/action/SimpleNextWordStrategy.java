package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SimpleNextWordStrategy implements NextRandomWord {
    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private UpdateLanguagePairHealth updateLanguagePairHealth;

    @Autowired
    private IntroduceNewWordForPractice introduceNewWordForPractice;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private FindPracticeWord findPracticeWord;

    @Autowired
    private FindUser findUser;

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;


    //TODO can be simpler
    @Override
    public PracticeWordTO nextWord(String userId, String languagePairId, OrderType orderType) {
        LanguagePairTO languagePairTO = findLanguagePair.byId(languagePairId);

        if (languagePairTO.practiceHealth().healthy()) {
            updateLanguagePairHealth.newWordIntroduced(languagePairId);
            Optional<TranslationPractice> translationPractice = introduceNewWordForPractice.forUserInLanguage(userId, languagePairTO.id());
            if (translationPractice.isPresent()) {
                TranslationPractice practice = translationPractice.get();
                Translation translation = translationRepository.findById(practice.getTranslationId()).orElseThrow(() -> new IllegalStateException("expected to find translation"));
                return findPracticeWord.forTranslation(translation.getId().toString(), orderType);
            }

        }

        List<PracticeWordTO> practiceWordTOS = giveCurrentListToPractice(userId, languagePairId, orderType);
        int index = (int) Math.floor(Math.random() * practiceWordTOS.size());
        return practiceWordTOS.get(index);
    }

    //TODO can be simpler
    public List<PracticeWordTO> giveCurrentListToPractice(String userId, String languagePairId, OrderType order) {
        UserTO user = findUser.byUserId(userId);

        List<TranslationPractice> translationPractices = findWordToPracticeFor(new ObjectId(userId), languagePairId);

        if (translationPractices.size() < user.userSettings().initialNumberOnPracticeWords()) {
            addNewWordToPracticeList(translationPractices, user.userSettings().initialNumberOnPracticeWords() - translationPractices.size(), languagePairId);
        }
        //if no words find to practice, check new words and add
        List<Translation> translations = StreamSupport.stream(translationRepository.findAllById(translationPractices.stream().map(TranslationPractice::getTranslationId).collect(Collectors.toList())).spliterator(), false).collect(Collectors.toList());

        return findPracticeWord.forTranslations(translations.stream().map(Translation::getId).map(ObjectId::toString).collect(Collectors.toList()), order);
    }

    private void addNewWordToPracticeList(List<TranslationPractice> translationPractices, Integer initialNumberOnPracticeWords, String languagePairId) {
        LanguagePairTO languagePair = findLanguagePair.byId(languagePairId);
        for (int i = 0; i < initialNumberOnPracticeWords; i++) {
            updateLanguagePairHealth.newWordIntroduced(languagePairId);
            introduceNewWordForPractice.forUserInLanguage(languagePair.userId(), languagePair.id()).ifPresent(translationPractices::add);
        }
    }


    private List<TranslationPractice> findWordToPracticeFor(ObjectId userId, String languagePairId) {
        return translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatus(userId, new ObjectId(languagePairId), ProgressStatus.IN_PROGRESS);
    }
}

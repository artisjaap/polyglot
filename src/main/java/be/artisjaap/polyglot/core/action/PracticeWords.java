package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.action.to.WordStatsTO;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PracticeWords {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private FindUser findUser;

    @Autowired
    private IntroduceNewWordForPractice introduceNewWordForPractice;

    @Autowired
    private LanguagePairTurn languagePairTurn;

    public PracticeWordTO nextWord(String userId, String languageFrom, String languageTo ){

        LanguagePairTO languagePairTO = findLanguagePair.pairForUser(userId, languageFrom, languageTo).orElseThrow(() -> new IllegalStateException(""));

        if(languagePairTO.turnsDone()> 0 && languagePairTO.turnsDone() % 20 == 0){
            Optional<TranslationPractice> translationPractice = introduceNewWordForPractice.forUserInLanguage(userId, languagePairTO.id());
            if(translationPractice.isPresent()){
                TranslationPractice practice = translationPractice.get();
                Translation translation = translationRepository.findById(practice.getTranslationId()).orElseThrow(() -> new IllegalStateException("expected to find translation"));
                PracticeWordTO merge = merge(practice, translation, languagePairTO, languageFrom, languageTo);
                System.out.println("New word:" + merge.question());
                return merge;
            }

        }

        List<PracticeWordTO> practiceWordTOS = giveCurrentListToPractice(userId, languageFrom, languageTo);
        int index = (int)Math.floor(Math.random() * practiceWordTOS.size());
        PracticeWordTO practiceWordTO = practiceWordTOS.get(index);
        System.out.println(practiceWordTO.question());
        return practiceWordTO;


    }


    public void practiced(String userId, String translationId, Boolean reversed){
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        saveTranslationTurn(translationPractice, reversed);
        languagePairTurn.saveTurnOn(translationPractice.getLanguagePairId().toString(), reversed);

    }

    private void saveTranslationTurn(TranslationPractice translationPractice, Boolean reversed) {
        if (reversed) {
            translationPractice.increaseAnswerChecked();
        }else {
            translationPractice.increaseAnswerCheckedReverse();
        }
        translationPracticeRepository.save(translationPractice);
    }

    public List<PracticeWordTO> giveCurrentListToPractice(String userId, String languageFrom, String languageTo ) {

        UserTO user = findUser.byUserId(userId);
        LanguagePairTO languagePair = findLanguagePair.pairForUserOrCreate(userId, languageFrom, languageTo);

        List<TranslationPractice> translationPractices = findWordToPracticeFor(new ObjectId(userId), languagePair);

        if(translationPractices.isEmpty()){
            addNewWordToPracticeList(translationPractices, user.userSettings().initialNumberOnPracticeWords(), languagePair);
        }
        //if no words find to practice, check new words and add
        List<Translation> translations = StreamSupport.stream(translationRepository.findAllById(translationPractices.stream().map(TranslationPractice::getTranslationId).collect(Collectors.toList())).spliterator(), false).collect(Collectors.toList());

        return merge(translationPractices, translations, languagePair, languageFrom, languageTo);
    }

    private void addNewWordToPracticeList(List<TranslationPractice> translationPractices, Integer initialNumberOnPracticeWords, LanguagePairTO languagePair) {
        for(int i =0; i < initialNumberOnPracticeWords; i++){
            introduceNewWordForPractice.forUserInLanguage(languagePair.userId(), languagePair.id()).ifPresent(translationPractices::add);
        }
    }

    private List<PracticeWordTO> merge(List<TranslationPractice> translationPractices, List<Translation> translations, LanguagePairTO languagePairTO, String languageFrom, String languageTo) {
        if(translationPractices.size() != translations.size()){
            return new ArrayList<>();
        }

        translationPractices.sort(Comparator.comparing(TranslationPractice::getTranslationId));
        translations.sort(Comparator.comparing(Translation::getId));

        List<PracticeWordTO> result = new ArrayList<>();
        for(int i = 0; i < translationPractices.size(); i++){
            TranslationPractice translationPractice = translationPractices.get(i);
            Translation translation = translations.get(i);
            PracticeWordTO merge = merge(translationPractice, translation, languagePairTO, languageFrom, languageTo);
            result.add(merge);
        }
        return result;

    }

    private PracticeWordTO merge(TranslationPractice translationPractice, Translation translation, LanguagePairTO languagePairTO, String languageQuestion, String languageAnswer){
        WordStatsTO stats = WordStatsTO.newBuilder()
                .withAnswerChecked(translationPractice.getAnswerChecked())
                .withAnswerCheckedReverse(translationPractice.getAnswerCheckedReverse())
                .withKnowledgeCounterMiss(translationPractice.getKnowledgeCounterMiss())
                .withKnowledgeCounterMissReverse(translationPractice.getKnowledgeCounterMissReverse())
                .withKnowledgeCounterSuccess(translationPractice.getKnowledgeCounterSuccess())
                .withKnowledgeCounterSuccessReverse(translationPractice.getKnowledgeCounterSuccessReverse())
                .withLastMiss(translationPractice.getLastMiss())
                .withLastMissReverse(translationPractice.getLastMissReverse())
                .withLastSuccess(translationPractice.getLastSuccess())
                .withLastSuccessReverse(translationPractice.getLastSuccessReverse())
                .withProgressStatus(translationPractice.getProgressStatus())
                .withKnowledgeStatus(translationPractice.getKnowledgeStatus())
                .withKnowledgeStatusReverse(translationPractice.getKnowledgeStatusReverse())
                .build();

        return PracticeWordTO.newBuilder()
                .withQuestionLanguage(languageQuestion)
                .withAnwserLanguage(languageAnswer)
                .withQuestion(deriveQuestion(translation, languagePairTO, languageQuestion))
                .withTranslationId(translation.getId().toString())
                .withWordStatsTO(stats)
                .withIsReversed(languagePairTO.languageFrom().equals(languageAnswer))
                .build();

    }

    private String deriveQuestion(Translation translation, LanguagePairTO languagePairTO, String languageQuestion) {
        return languagePairTO.languageFrom().equals(languageQuestion)?translation.getLanguageA():translation.getLanguageB();
    }

    private List<TranslationPractice> findWordToPracticeFor(ObjectId userId, LanguagePairTO languagePairTO) {
        return translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatus(userId, new ObjectId(languagePairTO.id()), ProgressStatus.IN_PROGRESS);
    }

    public PracticeWordTO answerWord(String translationId, String anwserLanguage, String answer){
        return null;
    }
}

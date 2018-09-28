package be.artisjaap.polyglot.core.action;

import be.artisjaap.core.validation.ValidationException;
import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
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

    @Autowired
    private TranslationPracticeAssembler translationPracticeAssembler;


    public PracticeWordTO nextWord(String userId, String languagePairId, OrderType orderType) {
        LanguagePairTO languagePairTO = findLanguagePair.byId(languagePairId).orElseThrow(() -> new ValidationException(""));

        if (languagePairTO.turnsDone() > 0 && languagePairTO.turnsDone() % 20 == 0) {
            Optional<TranslationPractice> translationPractice = introduceNewWordForPractice.forUserInLanguage(userId, languagePairTO.id());
            if (translationPractice.isPresent()) {
                TranslationPractice practice = translationPractice.get();
                Translation translation = translationRepository.findById(practice.getTranslationId()).orElseThrow(() -> new IllegalStateException("expected to find translation"));
                return merge(practice, translation, languagePairTO, orderType);
            }

        }

        List<PracticeWordTO> practiceWordTOS = giveCurrentListToPractice(userId, languagePairTO, orderType);
        int index = (int) Math.floor(Math.random() * practiceWordTOS.size());
        return practiceWordTOS.get(index);
    }








    @Deprecated()
    //use above method
    public PracticeWordTO nextWord(String userId, String languageFrom, String languageTo) {

        LanguagePairTO languagePairTO = findLanguagePair.pairForUser(userId, languageFrom, languageTo).orElseThrow(() -> new IllegalStateException(""));
        OrderType orderType = languagePairTO.languageFrom().equalsIgnoreCase(languageFrom) ? OrderType.NORMAL : OrderType.REVERSE;

        return nextWord(userId, languagePairTO.id(), orderType);

    }

    //todo wrap return with result of check
    public PracticeWordTO checkAnswerAndGiveNext(String userId, String translationId, String languageFrom, String languageTo, String answerGiven) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        LanguagePairTO languagePairTO = findLanguagePair.pairForUser(userId, languageFrom, languageTo).orElseThrow(() -> new IllegalStateException(""));
        Translation translation = translationRepository.findById(new ObjectId(translationId)).orElseThrow(IllegalStateException::new);


        if (languageFrom.equals(languagePairTO.languageFrom())) {
            if (translation.getLanguageB().equalsIgnoreCase(answerGiven)) {
                translationPractice.answerCorrect();
            } else {
                translationPractice.answerIncorrect();
            }

        } else if (languageTo.equals(languagePairTO.languageFrom())) {
            if (translation.getLanguageB().equalsIgnoreCase(answerGiven)) {
                translationPractice.answerCorrectReverse();
            } else {
                translationPractice.answerIncorrectReverse();
            }

        }

        translationPracticeRepository.save(translationPractice);

        return nextWord(userId, languageFrom, languageTo);
    }

    public void practiced(String userId, String translationId, Boolean reversed) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        saveTranslationTurn(translationPractice, reversed);
        languagePairTurn.saveTurnOn(translationPractice.getLanguagePairId().toString(), reversed);

    }

    private void saveTranslationTurn(TranslationPractice translationPractice, Boolean reversed) {
        if (reversed) {
            translationPractice.increaseAnswerChecked();
        } else {
            translationPractice.increaseAnswerCheckedReverse();
        }
        translationPracticeRepository.save(translationPractice);
    }

    public List<PracticeWordTO> giveCurrentListToPractice(String userId, LanguagePairTO languagePairTO, OrderType order) {
        UserTO user = findUser.byUserId(userId);

        List<TranslationPractice> translationPractices = findWordToPracticeFor(new ObjectId(userId), languagePairTO);

        if (translationPractices.isEmpty()) {
            addNewWordToPracticeList(translationPractices, user.userSettings().initialNumberOnPracticeWords(), languagePairTO);
        }
        //if no words find to practice, check new words and add
        List<Translation> translations = StreamSupport.stream(translationRepository.findAllById(translationPractices.stream().map(TranslationPractice::getTranslationId).collect(Collectors.toList())).spliterator(), false).collect(Collectors.toList());

        return merge(translationPractices, translations, languagePairTO, order);
    }

    @Deprecated
    public List<PracticeWordTO> giveCurrentListToPractice(String userId, String languageFrom, String languageTo) {

        LanguagePairTO languagePairTO = findLanguagePair.pairForUserOrCreate(userId, languageFrom, languageTo);

        OrderType orderType = languagePairTO.languageFrom().equalsIgnoreCase(languageFrom) ? OrderType.NORMAL : OrderType.REVERSE;

        return giveCurrentListToPractice(userId, languagePairTO, orderType);
    }

    public List<TranslationPracticeTO> allPracticedWords(String userId, String languagePairId, List<ProgressStatus> progressStatuses) {
        return translationPracticeAssembler.assembleTOs(translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatusIn(new ObjectId(userId), new ObjectId(languagePairId), progressStatuses));
    }

    public List<PracticeWordTO> givePracticeWordsForTranslations(String userId, String languageFrom, String languageTo, List<String> translationIds) {
        List<ObjectId> translationIdsObject = translationIds.stream().map(ObjectId::new).collect(Collectors.toList());
        List<Translation> translations = StreamSupport.stream(translationRepository
                .findAllById(translationIdsObject).spliterator(), false).collect(Collectors.toList());

        List<TranslationPractice> translationPractices = translationPracticeRepository.findByUserIdAndTranslationIdIn(new ObjectId(userId), translationIdsObject);
        LanguagePairTO languagePair = findLanguagePair.pairForUserOrCreate(userId, languageFrom, languageTo);

        return merge(translationPractices, translations, languagePair, languageFrom, languageTo);
    }

    private void addNewWordToPracticeList(List<TranslationPractice> translationPractices, Integer initialNumberOnPracticeWords, LanguagePairTO languagePair) {
        for (int i = 0; i < initialNumberOnPracticeWords; i++) {
            introduceNewWordForPractice.forUserInLanguage(languagePair.userId(), languagePair.id()).ifPresent(translationPractices::add);
        }
    }

    private List<PracticeWordTO> merge(List<TranslationPractice> translationPractices, List<Translation> translations, LanguagePairTO languagePairTO, OrderType orderType) {
        switch (orderType) {
            case NORMAL:
                return merge(translationPractices, translations, languagePairTO, languagePairTO.languageFrom(), languagePairTO.languageTo());
            case REVERSE:
                return merge(translationPractices, translations, languagePairTO, languagePairTO.languageTo(), languagePairTO.languageFrom());
            case RANDOM: // TODO randomize
                return merge(translationPractices, translations, languagePairTO, languagePairTO.languageFrom(), languagePairTO.languageTo());

        }
        return new ArrayList<>();
    }

    @Deprecated
    private List<PracticeWordTO> merge(List<TranslationPractice> translationPractices, List<Translation> translations, LanguagePairTO languagePairTO, String languageFrom, String languageTo) {
        if (translationPractices.size() != translations.size()) {
            return new ArrayList<>();
        }

        translationPractices.sort(Comparator.comparing(TranslationPractice::getTranslationId));
        translations.sort(Comparator.comparing(Translation::getId));

        List<PracticeWordTO> result = new ArrayList<>();
        for (int i = 0; i < translationPractices.size(); i++) {
            TranslationPractice translationPractice = translationPractices.get(i);
            Translation translation = translations.get(i);
            PracticeWordTO merge = merge(translationPractice, translation, languagePairTO, languageFrom, languageTo);
            result.add(merge);
        }
        return result;

    }


    private PracticeWordTO merge(TranslationPractice translationPractice, Translation translation, LanguagePairTO languagePairTO, OrderType orderType) {
        switch (orderType) {
            case NORMAL:
                return merge(translationPractice, translation, languagePairTO, languagePairTO.languageFrom(), languagePairTO.languageTo());
            case REVERSE:
                return merge(translationPractice, translation, languagePairTO, languagePairTO.languageTo(), languagePairTO.languageFrom());
            case RANDOM: // TODO randomize
                return merge(translationPractice, translation, languagePairTO, languagePairTO.languageFrom(), languagePairTO.languageTo());

        }
        return null;//FIXME throw exception
    }

    @Deprecated
    private PracticeWordTO merge(TranslationPractice translationPractice, Translation translation, LanguagePairTO languagePairTO, String languageQuestion, String languageAnswer) {
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
                .withAnswer(deriveAnswer(translation, languagePairTO, languageQuestion))
                .build();

    }

    private String deriveQuestion(Translation translation, LanguagePairTO languagePairTO, String languageQuestion) {
        return languagePairTO.languageFrom().equals(languageQuestion) ? translation.getLanguageA() : translation.getLanguageB();
    }

    private String deriveAnswer(Translation translation, LanguagePairTO languagePairTO, String languageQuestion) {
        return languagePairTO.languageFrom().equals(languageQuestion) ? translation.getLanguageB() : translation.getLanguageA();
    }

    private List<TranslationPractice> findWordToPracticeFor(ObjectId userId, LanguagePairTO languagePairTO) {
        return translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatus(userId, new ObjectId(languagePairTO.id()), ProgressStatus.IN_PROGRESS);
    }

    public PracticeWordTO answerWord(String translationId, String anwserLanguage, String answer) {
        return null;
    }
}

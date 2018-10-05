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

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static be.artisjaap.polyglot.core.action.to.test.OrderType.REVERSE;

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

        List<PracticeWordTO> practiceWordTOS = giveCurrentListToPractice(userId, languagePairId, orderType);
        int index = (int) Math.floor(Math.random() * practiceWordTOS.size());
        return practiceWordTOS.get(index);
    }


    //todo wrap return with result of check
    public PracticeWordTO checkAnswerAndGiveNext(String userId, String translationId, String answerGiven, OrderType answerOrderType, OrderType nextOrderType) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        Translation translation = translationRepository.findById(new ObjectId(translationId)).orElseThrow(IllegalStateException::new);
        LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString()).orElseThrow(IllegalStateException::new);


        if (answerOrderType == NORMAL) {
            if (translation.getLanguageB().equalsIgnoreCase(answerGiven)) {
                translationPractice.answerCorrect();
            } else {
                translationPractice.answerIncorrect();
            }

        } else if (answerOrderType == REVERSE) {
            if (translation.getLanguageB().equalsIgnoreCase(answerGiven)) {
                translationPractice.answerCorrectReverse();
            } else {
                translationPractice.answerIncorrectReverse();
            }

        }

        translationPracticeRepository.save(translationPractice);

        return nextWord(userId, languagePairTO.id(), nextOrderType);
    }


    public void practiced(String userId, String translationId, Boolean reversed) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        saveTranslationTurn(translationPractice, reversed);
        languagePairTurn.saveTurnOn(translationPractice.getLanguagePairId().toString(), reversed);

    }

    public List<PracticeWordTO> giveCurrentListToPractice(String userId, String languagePairId, OrderType order) {
        UserTO user = findUser.byUserId(userId);

        List<TranslationPractice> translationPractices = findWordToPracticeFor(new ObjectId(userId), languagePairId);

        if (translationPractices.isEmpty()) {
            addNewWordToPracticeList(translationPractices, user.userSettings().initialNumberOnPracticeWords(), languagePairId);
        }
        //if no words find to practice, check new words and add
        List<Translation> translations = StreamSupport.stream(translationRepository.findAllById(translationPractices.stream().map(TranslationPractice::getTranslationId).collect(Collectors.toList())).spliterator(), false).collect(Collectors.toList());

        return merge(translationPractices, translations, order);
    }

    public List<TranslationPracticeTO> allPracticedWords(String userId, String languagePairId, List<ProgressStatus> progressStatuses) {
        return translationPracticeAssembler.assembleTOs(translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatusIn(new ObjectId(userId), new ObjectId(languagePairId), progressStatuses));
    }

    public List<PracticeWordTO> givePracticeWordsForTranslations(String userId, OrderType orderType, List<String> translationIds) {
        List<ObjectId> translationIdsObject = translationIds.stream().map(ObjectId::new).collect(Collectors.toList());
        List<Translation> translations = StreamSupport.stream(translationRepository
                .findAllById(translationIdsObject).spliterator(), false).collect(Collectors.toList());

        List<TranslationPractice> translationPractices = translationPracticeRepository.findByUserIdAndTranslationIdIn(new ObjectId(userId), translationIdsObject);

        return merge(translationPractices, translations, orderType);
    }

















    private PracticeWordTO merge(TranslationPractice translationPractice, Translation translation, LanguagePairTO languagePairTO, OrderType orderType) {
        OrderType orderWithoutRandom = toOrderTypeWithoutRandom(orderType);
        String languageFrom;
        String languageTo;

        if(orderWithoutRandom == OrderType.NORMAL){
            languageFrom = languagePairTO.languageFrom();
            languageTo = languagePairTO.languageTo();
        }else{
            languageFrom = languagePairTO.languageTo();
            languageTo = languagePairTO.languageFrom();
        }

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
                .withQuestionLanguage(languageFrom)
                .withAnwserLanguage(languageTo)
                .withQuestion(deriveQuestion(translation, orderWithoutRandom))
                .withTranslationId(translation.getId().toString())
                .withWordStatsTO(stats)
                .withIsReversed(orderWithoutRandom == REVERSE)
                .withAnswer(deriveAnswer(translation, orderType))
                .build();

    }

    private OrderType toOrderTypeWithoutRandom(OrderType orderType) {
        switch (orderType){
            case NORMAL:
            case REVERSE:
                return orderType;
            default:
                return Math.floor(Math.random()*2)%2 == 1? OrderType.NORMAL: OrderType.REVERSE;
        }
    }

    private String deriveQuestion(Translation translation, OrderType orderType) {
        return orderType == NORMAL ? translation.getLanguageA() : translation.getLanguageB();
    }

    private String deriveAnswer(Translation translation, OrderType orderType) {
        return orderType == NORMAL ? translation.getLanguageB() : translation.getLanguageA();
    }



    private void saveTranslationTurn(TranslationPractice translationPractice, Boolean reversed) {
        if (reversed) {
            translationPractice.increaseAnswerChecked();
        } else {
            translationPractice.increaseAnswerCheckedReverse();
        }
        translationPracticeRepository.save(translationPractice);
    }



    private List<PracticeWordTO> merge(List<TranslationPractice> translationPractices, List<Translation> translations, OrderType orderType) {
        if (translationPractices.size() != translations.size()) {
            return new ArrayList<>();
        }

        translationPractices.sort(Comparator.comparing(TranslationPractice::getTranslationId));
        translations.sort(Comparator.comparing(Translation::getId));

        List<PracticeWordTO> result = new ArrayList<>();
        for (int i = 0; i < translationPractices.size(); i++) {
            TranslationPractice translationPractice = translationPractices.get(i);
            Translation translation = translations.get(i);
            LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString()).orElseThrow(IllegalStateException::new);
            PracticeWordTO merge = merge(translationPractice, translation, languagePairTO, orderType);
            result.add(merge);
        }
        return result;

    }



    private void addNewWordToPracticeList(List<TranslationPractice> translationPractices, Integer initialNumberOnPracticeWords, String languagePairId) {
        LanguagePairTO languagePair = findLanguagePair.byId(languagePairId).orElseThrow(IllegalStateException::new);
        for (int i = 0; i < initialNumberOnPracticeWords; i++) {
            introduceNewWordForPractice.forUserInLanguage(languagePair.userId(), languagePair.id()).ifPresent(translationPractices::add);
        }
    }


    private List<TranslationPractice> findWordToPracticeFor(ObjectId userId, String languagePairId) {
        return translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatus(userId, new ObjectId(languagePairId), ProgressStatus.IN_PROGRESS);
    }


}

package be.artisjaap.polyglot.core.action.lessonpractice;

import be.artisjaap.common.utils.ListUtils;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.WordStatsTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Set;

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static be.artisjaap.polyglot.core.action.to.test.OrderType.REVERSE;

@Component
public class PracticeWordBuilder {

    private final TranslationPracticeRepository translationPracticeRepository;
    private final TranslationRepository translationRepository;
    private final FindLanguagePair findLanguagePair;

    public PracticeWordBuilder(TranslationPracticeRepository translationPracticeRepository,
                               TranslationRepository translationRepository,
                               FindLanguagePair findLanguagePair) {
        this.translationPracticeRepository = translationPracticeRepository;
        this.translationRepository = translationRepository;
        this.findLanguagePair = findLanguagePair;
    }

    public PracticeWordTO forTranslation(String translationId, OrderType orderType) {

        if (null == translationId) {
            return PracticeWordTO.newBuilder().withEnded(true).build();
        }

        ObjectId translationObjectId = new ObjectId(translationId);
        TranslationPractice translationPractice = translationPracticeRepository.findByTranslationId(translationObjectId).orElseThrow();
        Translation translation = translationRepository.findById(translationObjectId).orElseThrow();
        LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());

        OrderType orderWithoutRandom = toOrderTypeWithoutRandom(orderType);
        String languageFrom;
        String languageTo;

        if (orderWithoutRandom == OrderType.NORMAL) {
            languageFrom = languagePairTO.languageFrom();
            languageTo = languagePairTO.languageTo();
        } else {
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
                .withEnded(false)
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
        switch (orderType) {
            case NORMAL:
            case REVERSE:
                return orderType;
            default:
                return Math.floor(Math.random() * 2) % 2 == 1 ? OrderType.NORMAL : OrderType.REVERSE;
        }
    }

    private String deriveQuestion(Translation translation, OrderType orderType) {
        return orderType == NORMAL ? ListUtils.getRandomFromCollection(translation.getLanguageA()) :
                ListUtils.getRandomFromCollection(translation.getLanguageB());
    }

    private Set<String> deriveAnswer(Translation translation, OrderType orderType) {
        return orderType == NORMAL ? translation.getLanguageB() : translation.getLanguageA();
    }
}

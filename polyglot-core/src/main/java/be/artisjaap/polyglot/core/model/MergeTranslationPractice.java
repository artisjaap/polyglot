package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.WordStatsTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static be.artisjaap.polyglot.core.action.to.test.OrderType.REVERSE;

@Component
public class MergeTranslationPractice {
    @Autowired
    private FindLanguagePair findLanguagePair;

    public List<PracticeWordTO> merge(List<TranslationPractice> translationPractices, List<Translation> translations, OrderType orderType) {
        if (translationPractices.size() != translations.size()) {
            return new ArrayList<>();
        }

        translationPractices.sort(Comparator.comparing(TranslationPractice::getTranslationId));
        translations.sort(Comparator.comparing(Translation::getId));

        List<PracticeWordTO> result = new ArrayList<>();
        for (int i = 0; i < translationPractices.size(); i++) {
            TranslationPractice translationPractice = translationPractices.get(i);
            Translation translation = translations.get(i);
            LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());
            PracticeWordTO merge = merge(translationPractice, translation, languagePairTO, orderType);
            result.add(merge);
        }
        return result;

    }

    public PracticeWordTO merge(TranslationPractice translationPractice, Translation translation, LanguagePairTO languagePairTO, OrderType orderType) {
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
}

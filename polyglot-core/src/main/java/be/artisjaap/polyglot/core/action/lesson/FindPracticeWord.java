package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.common.validation.ValidationException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static be.artisjaap.polyglot.core.action.to.test.OrderType.REVERSE;

@Component
public class FindPracticeWord {

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private TranslationRepository translationRepository;


    public PracticeWordTO forTranslation(String translationId, OrderType orderType){
        ObjectId translationObjectId = new ObjectId(translationId);
        TranslationPractice translationPractice = translationPracticeRepository.findByTranslationId(translationObjectId).orElseThrow(() -> new ValidationException("TRANSLATION NOT FOUD"));
        Translation translation = translationRepository.findById(translationObjectId).orElseThrow(() -> new ValidationException("TRANSLATION NOT FOUD"));

        LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());
        return merge(translationPractice, translation, languagePairTO, orderType);
    }

    public List<PracticeWordTO> forTranslations(List<String> translations, OrderType orderType){
        List<ObjectId> translationObjectIds = translations.stream().map(ObjectId::new).collect(Collectors.toList());
        List<Translation> translationList = StreamSupport.stream(translationRepository.findAllById(translationObjectIds).spliterator(), false).collect(Collectors.toList());
        List<TranslationPractice> translationPracticeList = translationPracticeRepository.findByTranslationIdIn(translationObjectIds);
        return merge(translationPracticeList, translationList, orderType);
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
            LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());
            PracticeWordTO merge = merge(translationPractice, translation, languagePairTO, orderType);
            result.add(merge);
        }
        return result;

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
}

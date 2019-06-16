package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InitTranslationForPractice {
    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    public void forAll(TranslationsForUserTO translations){

        Set<ObjectId> existingTranslationIdsInPractice = translationPracticeRepository.findByUserIdAndTranslationIdIn(MongoUtils.toObjectId(translations.userId())
                , translations.translations().stream().map(TranslationTO::id)
                        .map(MongoUtils::toObjectId)
                        .collect(Collectors.toList()))
                .stream().map(TranslationPractice::getTranslationId)
                .collect(Collectors.toSet());


        List<TranslationPractice> translationPractices = translations.translations().stream()
                .map(TranslationTO::id)
                .map(MongoUtils::toObjectId)
                .filter(id -> !existingTranslationIdsInPractice.contains(id))
                .map(t -> TranslationPractice.newBuilder()
                        .withAnswerChecked(0)
                        .withAnswerCheckedReverse(0)
                        .withKnowledgeCounterMiss(0)
                        .withKnowledgeCounterMissReverse(0)
                        .withLanguagePairId(new ObjectId(translations.languagePairId()))
                        .withKnowledgeCounterSuccess(0)
                        .withKnowledgeCounterSuccessReverse(0)
                        .withKnowledgeStatus(KnowledgeStatus.NEW)
                        .withKnowledgeStatusReverse(KnowledgeStatus.NEW)
                        .withProgressStatus(ProgressStatus.NEW)
                        .withTranslationId(t)
                        .withUserId(new ObjectId(translations.userId()))
                        .build())
                .collect(Collectors.toList());
        translationPracticeRepository.saveAll(translationPractices);

    }
}

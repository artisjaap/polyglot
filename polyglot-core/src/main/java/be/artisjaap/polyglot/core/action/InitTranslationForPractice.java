package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitTranslationForPractice {
    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    public void forAll(TranslationsForUserTO translations){

        List<TranslationPractice> translationPractices = translations.translations().stream()
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
                        .withTranslationId(new ObjectId(t.id()))
                        .withUserId(new ObjectId(translations.userId()))
                        .build())
                .collect(Collectors.toList());
        translationPracticeRepository.saveAll(translationPractices);

    }
}

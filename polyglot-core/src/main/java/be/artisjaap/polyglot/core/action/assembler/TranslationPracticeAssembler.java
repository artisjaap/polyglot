package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.TranslationPracticeTO;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class TranslationPracticeAssembler implements Assembler<TranslationPracticeTO, TranslationPractice> {
    @Override
    public TranslationPracticeTO assembleTO(TranslationPractice doc) {
        return TranslationPracticeTO.newBuilder()
                .forDocument(doc)
                .withTranslationId(doc.getTranslationId().toString())
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withUserId(doc.getUserId().toString())
                .withProgressStatus(doc.getProgressStatus())
                .withKnowledgeStatus(doc.getKnowledgeStatus())
                .withKnowledgeStatusReverse(doc.getKnowledgeStatusReverse())
                .withKnowledgeCounterSuccess(doc.getKnowledgeCounterSuccess())
                .withKnowledgeCounterSuccessReverse(doc.getKnowledgeCounterSuccessReverse())
                .withKnowledgeCounterMiss(doc.getKnowledgeCounterMiss())
                .withKnowledgeCounterMissReverse(doc.getKnowledgeCounterMissReverse())
                .withLastSuccess(doc.getLastSuccess())
                .withLastSuccessReverse(doc.getLastSuccessReverse())
                .withLastMiss(doc.getLastMiss())
                .withLastMissReverse(doc.getLastMissReverse())
                .withAnswerChecked(doc.getAnswerChecked())
                .withAnswerCheckedReverse(doc.getAnswerCheckedReverse())
                .build();

    }

    @Override
    public TranslationPractice assembleEntity(TranslationPracticeTO translationPracticeTO) {
        return TranslationPractice.newBuilder()
                .forDocument(translationPracticeTO)
                .withTranslationId(new ObjectId(translationPracticeTO.translationId()))
                .withLanguagePairId(new ObjectId(translationPracticeTO.languagePairId()))
                .withUserId(new ObjectId(translationPracticeTO.userId()))
                .withProgressStatus(translationPracticeTO.progressStatus())
                .withKnowledgeStatus(translationPracticeTO.knowledgeStatus())
                .withKnowledgeStatusReverse(translationPracticeTO.knowledgeStatusReverse())
                .withKnowledgeCounterSuccess(translationPracticeTO.knowledgeCounterSuccess())
                .withKnowledgeCounterSuccessReverse(translationPracticeTO.knowledgeCounterSuccessReverse())
                .withKnowledgeCounterMiss(translationPracticeTO.knowledgeCounterMiss())
                .withKnowledgeCounterMissReverse(translationPracticeTO.knowledgeCounterMissReverse())
                .withLastSuccess(translationPracticeTO.lastSuccess())
                .withLastSuccessReverse(translationPracticeTO.lastSuccessReverse())
                .withLastMiss(translationPracticeTO.lastMiss())
                .withLastMissReverse(translationPracticeTO.lastMissReverse())
                .withAnswerChecked(translationPracticeTO.answerChecked())
                .withAnswerCheckedReverse(translationPracticeTO.answerCheckedReverse())
                .build();
    }
}

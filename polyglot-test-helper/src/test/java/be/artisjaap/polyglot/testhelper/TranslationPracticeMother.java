package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.TranslationPractice;
import org.bson.types.ObjectId;

public class TranslationPracticeMother extends AbstractMother {

    public static TranslationPractice.Builder initRandom(){
        return TranslationPractice.newBuilder()
                .withTranslationId(new ObjectId())
                .withUserId(new ObjectId())
                .withProgressStatus(ProgressStatusMother.random())
                .withKnowledgeStatus(KnowledgeStatusMother.random())
                .withKnowledgeStatusReverse(KnowledgeStatusMother.random())
                .withKnowledgeCounterSuccess(fairy.baseProducer().randomBetween(0, 20))
                .withKnowledgeCounterSuccessReverse(fairy.baseProducer().randomBetween(0, 20))
                .withKnowledgeCounterMiss(fairy.baseProducer().randomBetween(0, 20))
                .withKnowledgeCounterMissReverse(fairy.baseProducer().randomBetween(0, 20))
                .withLastSuccess(from(fairy.dateProducer().randomDateInThePast(1)))
                .withLastSuccessReverse(from(fairy.dateProducer().randomDateInThePast(1)))
                .withLastMiss(from(fairy.dateProducer().randomDateInThePast(1)))
                .withLastMissReverse(from(fairy.dateProducer().randomDateInThePast(1)))
                .withLanguagePairId(new ObjectId())
                .withAnswerChecked(fairy.baseProducer().randomBetween(0, 20))
                .withAnswerCheckedReverse(fairy.baseProducer().randomBetween(0, 20));
    }
}

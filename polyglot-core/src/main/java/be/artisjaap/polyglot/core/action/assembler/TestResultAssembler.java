package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.to.test.TestResultTO;
import be.artisjaap.polyglot.core.model.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestResultAssembler implements Assembler<TestResultTO, TestResult> {
    @Autowired
    private WordResultAssembler wordResultAssembler;

    @Override
    public TestResultTO assembleTO(TestResult doc) {
        return TestResultTO.newBuilder()
                .withScore(doc.getScore())
                .withWordResults(wordResultAssembler.assembleTOs(doc.getWordResults()))
                .withLessonName(doc.getLessonName())
                .withLessonId(doc.getLessonId().toString())
                .withUserId(doc.getUserId().toString())
                .build();
    }

    @Override
    public TestResult assembleEntity(TestResultTO to) {
        return TestResult.newBuilder()
                .withScore(to.score())
                .withWordResults(wordResultAssembler.assembleDocuments(to.wordResults()))
                .withLessonName(to.lessonName())
                .withLessonId(MongoUtils.toObjectId(to.lessonId()))
                .withUserId(MongoUtils.toObjectId(to.userId()))
                .build();
    }
}

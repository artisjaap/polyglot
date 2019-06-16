package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.to.test.WordResultTO;
import be.artisjaap.polyglot.core.model.WordResult;
import org.springframework.stereotype.Component;

@Component
public class WordResultAssembler implements Assembler<WordResultTO, WordResult> {

    @Override
    public WordResultTO assembleTO(WordResult doc) {
        return WordResultTO.newBuilder()
                .withCorrect(doc.getCorrect())
                .withExpectedAnswer(doc.getExpectedAnswer())
                .withGivenAnswer(doc.getGivenAnswer())
                .withQuestion(doc.getQuestion())
                .withTranslationId(doc.getTranslationId().toString())
                .build();
    }

    @Override
    public WordResult assembleEntity(WordResultTO to) {
        return WordResult.newBuilder()
                .withCorrect(to.correct())
                .withExpectedAnswer(to.expectedAnswer())
                .withGivenAnswer(to.givenAnswer())
                .withQuestion(to.question())
                .withTranslationId(MongoUtils.toObjectId(to.translationId()))
                .build();
    }
}

package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.mistakes.LanguageMistakeDetailTO;
import be.artisjaap.polyglot.core.model.MistakeDetail;
import org.springframework.stereotype.Component;

@Component
public class LanguageMistakeDetailAssembler implements Assembler<LanguageMistakeDetailTO, MistakeDetail> {
    @Override
    public LanguageMistakeDetailTO assembleTO(MistakeDetail doc) {
        return LanguageMistakeDetailTO.builder()
                .translationId(doc.getTranslationId().toString())
                .question(doc.getQuestion())
                .correctAnswer(doc.getCorrectAnswer())
                .timesCorrect(doc.getTimesCorrect())
                .timesIncorrect(doc.getTimesIncorrect())
                .total(doc.total())
                .givenAnswers(doc.getGivenAnswers())
                .build();
    }

    @Override
    public MistakeDetail assembleEntity(LanguageMistakeDetailTO languageMistakeDetailTO) {
        throw new UnsupportedOperationException();
    }
}

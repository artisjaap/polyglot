package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.AnswerTO;
import org.springframework.stereotype.Component;

@Component
public class PracticeAnswerResponseMapper implements ResponseMapper<AnswerTO, PracticeAnswerResponse> {
    @Override
    public PracticeAnswerResponse map(AnswerTO answerTO) {
        return PracticeAnswerResponse.builder()
                .correctAnswer(answerTO.correctAnswer())
                .expectedAnswer(answerTO.expectedAnswer())
                .givenAnswer(answerTO.givenAnswer())
                .languagePairId(answerTO.languagePairId())
                .question(answerTO.question())
                .translationId(answerTO.translationId())
                .userId(answerTO.userId())
                .build();
    }
}

package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.AnswerTO;
import org.springframework.stereotype.Component;

@Component
public class PracticeAnswerResponseMapper implements ResponseMapper<AnswerTO, PracticeAnswerResponse> {
    @Override
    public PracticeAnswerResponse map(AnswerTO answerTO) {
        return null;
    }
}

package be.artisjaap.polyglot.web.endpoints.request;

import be.artisjaap.polyglot.core.action.to.test.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PracticeAnswerValidateRequest {
    private String lessonId;
    private String translationId;
    private String answerGiven;
    private OrderType answerOrderType;
}

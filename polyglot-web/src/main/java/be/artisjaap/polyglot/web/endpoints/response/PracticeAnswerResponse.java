package be.artisjaap.polyglot.web.endpoints.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PracticeAnswerResponse   {
    private String translationId;
    private String userId;
    private String languagePairId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correctAnswer;
}

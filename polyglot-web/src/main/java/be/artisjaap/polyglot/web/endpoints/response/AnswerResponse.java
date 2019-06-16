package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.AnswerTO;

public class AnswerResponse {
    private String translationId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correctAnswer;

    private AnswerResponse(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        givenAnswer = builder.givenAnswer;
        expectedAnswer = builder.expectedAnswer;
        correctAnswer = builder.correctAnswer;
    }

    public String getTranslationId() {
        return translationId;
    }

    public String getQuestion() {
        return question;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public static AnswerResponse from(AnswerTO answer){
        return newBuilder()
                .withCorrectAnswer(answer.correctAnswer())
                .withExpectedAnswer(answer.expectedAnswer())
                .withGivenAnswer(answer.givenAnswer())
                .withQuestion(answer.question())
                .withTranslationId(answer.translationId()).build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String translationId;
        private String question;
        private String givenAnswer;
        private String expectedAnswer;
        private Boolean correctAnswer;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public Builder withGivenAnswer(String val) {
            givenAnswer = val;
            return this;
        }

        public Builder withExpectedAnswer(String val) {
            expectedAnswer = val;
            return this;
        }

        public Builder withCorrectAnswer(Boolean val) {
            correctAnswer = val;
            return this;
        }

        public AnswerResponse build() {
            return new AnswerResponse(this);
        }
    }
}

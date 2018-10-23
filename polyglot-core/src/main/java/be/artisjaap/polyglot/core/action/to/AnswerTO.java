package be.artisjaap.polyglot.core.action.to;

public class AnswerTO {
    private String translationId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correctAnswer;

    public String translationId() {
        return translationId;
    }

    public String question() {
        return question;
    }

    public String givenAnswer() {
        return givenAnswer;
    }

    public String expectedAnswer() {
        return expectedAnswer;
    }

    public Boolean correctAnswer() {
        return correctAnswer;
    }

    private AnswerTO(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        givenAnswer = builder.givenAnswer;
        expectedAnswer = builder.expectedAnswer;
        correctAnswer = builder.correctAnswer;
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

        public AnswerTO build() {
            return new AnswerTO(this);
        }

        public String translationId() {
            return translationId;
        }

        public String question() {
            return question;
        }

        public String givenAnswer() {
            return givenAnswer;
        }

        public String expectedAnswer() {
            return expectedAnswer;
        }

        public Boolean correctAnswer() {
            return correctAnswer;
        }
    }
}

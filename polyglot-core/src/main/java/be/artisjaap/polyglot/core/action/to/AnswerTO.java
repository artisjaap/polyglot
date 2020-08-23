package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class AnswerTO {
    private String translationId;
    private String userId;
    private String languagePairId;
    private String question;
    private String givenAnswer;
    private Set<String> expectedAnswers;
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

    public Set<String> expectedAnswer() {
        return expectedAnswers;
    }

    public Boolean correctAnswer() {
        return correctAnswer;
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    private AnswerTO(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        givenAnswer = builder.givenAnswer;
        expectedAnswers = builder.expectedAnswers;
        correctAnswer = builder.correctAnswer;
        userId = builder.userId;
        languagePairId = builder.languagePairId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String translationId;
        private String userId;
        private String languagePairId;
        private String question;
        private String givenAnswer;
        private Set<String> expectedAnswers;
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

        public Builder withExpectedAnswers(Set<String> val) {
            expectedAnswers = val;
            return this;
        }

        public Builder withCorrectAnswer(Boolean val) {
            correctAnswer = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public AnswerTO build() {
            return new AnswerTO(this);
        }


    }
}

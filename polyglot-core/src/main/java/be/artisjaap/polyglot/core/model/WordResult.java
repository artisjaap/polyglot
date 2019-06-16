package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;

public class WordResult {

    private ObjectId translationId;
    private String question;
    private String expectedAnswer;
    private String givenAnswer;
    private Boolean correct;

    private WordResult(){}

    private WordResult(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        expectedAnswer = builder.expectedAnswer;
        givenAnswer = builder.givenAnswer;
        correct = builder.correct;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ObjectId getTranslationId() {
        return translationId;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public static final class Builder {
        private ObjectId translationId;
        private String question;
        private String expectedAnswer;
        private String givenAnswer;
        private Boolean correct;

        private Builder() {
        }

        public Builder withTranslationId(ObjectId val) {
            translationId = val;
            return this;
        }

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public Builder withExpectedAnswer(String val) {
            expectedAnswer = val;
            return this;
        }

        public Builder withGivenAnswer(String val) {
            givenAnswer = val;
            return this;
        }

        public Builder withCorrect(Boolean val) {
            correct = val;
            return this;
        }

        public WordResult build() {
            return new WordResult(this);
        }
    }
}

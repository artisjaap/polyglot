package be.artisjaap.polyglot.core.action.to.test;

import java.util.Set;

public class WordResultTO {
    private String translationId;
    private String question;
    private Set<String> expectedAnswer;
    private String givenAnswer;
    private Boolean correct;

    private WordResultTO(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        expectedAnswer = builder.expectedAnswer;
        givenAnswer = builder.givenAnswer;
        correct = builder.correct;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String translationId() {
        return translationId;
    }

    public String question() {
        return question;
    }

    public Set<String> expectedAnswer() {
        return expectedAnswer;
    }

    public String givenAnswer() {
        return givenAnswer;
    }

    public Boolean correct() {
        return correct;
    }

    public static final class Builder {
        private String translationId;
        private String question;
        private Set<String> expectedAnswer;
        private String givenAnswer;
        private Boolean correct;

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

        public Builder withExpectedAnswer(Set<String> val) {
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

        public WordResultTO build() {
            return new WordResultTO(this);
        }
    }
}

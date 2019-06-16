package be.artisjaap.polyglot.core.action.to;

import java.time.LocalDateTime;

public class TranslationJournalTO {
    private LocalDateTime timestamp;
    private String translationId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correct;

    private TranslationJournalTO(Builder builder) {
        timestamp = builder.timestamp;
        translationId = builder.translationId;
        question = builder.question;
        givenAnswer = builder.givenAnswer;
        expectedAnswer = builder.expectedAnswer;
        correct = builder.correct;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public LocalDateTime timestamp() {
        return timestamp;
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

    public Boolean correct() {
        return correct;
    }

    public static final class Builder {
        private LocalDateTime timestamp;
        private String translationId;
        private String question;
        private String givenAnswer;
        private String expectedAnswer;
        private Boolean correct;

        private Builder() {
        }

        public Builder withTimestamp(LocalDateTime val) {
            timestamp = val;
            return this;
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

        public Builder withCorrect(Boolean val) {
            correct = val;
            return this;
        }

        public TranslationJournalTO build() {
            return new TranslationJournalTO(this);
        }
    }
}

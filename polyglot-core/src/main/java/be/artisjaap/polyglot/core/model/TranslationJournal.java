package be.artisjaap.polyglot.core.model;

import be.artisjaap.core.utils.LocalDateUtils;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class TranslationJournal {
    private LocalDateTime timestamp;
    private ObjectId translationId;
    private ObjectId lessonId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correct;

    private TranslationJournal(){}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ObjectId getTranslationId() {
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

    public Boolean getCorrect() {
        return correct;
    }

    public ObjectId getLessonId() {
        return lessonId;
    }

    private TranslationJournal(Builder builder) {
        timestamp = builder.timestamp;
        lessonId = builder.lessonId;
        translationId = builder.translationId;
        question = builder.question;
        givenAnswer = builder.givenAnswer;
        expectedAnswer = builder.expectedAnswer;
        correct = builder.correct;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private LocalDateTime timestamp = LocalDateUtils.now();
        private ObjectId translationId;
        private ObjectId lessonId;
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

        public Builder withTranslationId(ObjectId val) {
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

        public Builder withLessonId(ObjectId val) {
            lessonId = val;
            return this;
        }

        public TranslationJournal build() {
            return new TranslationJournal(this);
        }
    }
}

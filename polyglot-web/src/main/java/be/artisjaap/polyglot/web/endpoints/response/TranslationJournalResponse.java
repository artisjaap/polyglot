package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TranslationJournalResponse {

    private LocalDateTime timestamp;
    private String translationId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correct;

    public static TranslationJournalResponse from(TranslationJournalTO to){
        return TranslationJournalResponse.newBuilder()
                .withCorrect(to.correct())
                .withExpectedAnswer(to.expectedAnswer())
                .withGivenAnswer(to.givenAnswer())
                .withQuestion(to.question())
                .withTimestamp(to.timestamp())
                .withTranslationId(to.translationId())
                .build();
    }

    public static List<TranslationJournalResponse> from(Collection<TranslationJournalTO> to) {
        return to.stream().map(TranslationJournalResponse::from).collect(Collectors.toList());
    }


    private TranslationJournalResponse(Builder builder) {
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

    public LocalDateTime getTimestamp() {
        return timestamp;
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

    public Boolean getCorrect() {
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

        public TranslationJournalResponse build() {
            return new TranslationJournalResponse(this);
        }
    }
}

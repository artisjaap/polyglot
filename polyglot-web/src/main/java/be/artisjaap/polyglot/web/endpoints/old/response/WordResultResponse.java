package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.test.WordResultTO;

import java.util.List;
import java.util.stream.Collectors;

public class WordResultResponse {

    private String translationId;
    private String question;
    private String expectedAnswer;
    private String givenAnswer;
    private Boolean correct;

    private WordResultResponse(Builder builder) {
        translationId = builder.translationId;
        question = builder.question;
        expectedAnswer = builder.expectedAnswer;
        givenAnswer = builder.givenAnswer;
        correct = builder.correct;
    }

    public static List<WordResultResponse> from(List<WordResultTO> wordResult) {
        return wordResult.stream().map(WordResultResponse::from).collect(Collectors.toList());
    }

    public static WordResultResponse from(WordResultTO wordResult) {
        return WordResultResponse.newBuilder()
                .withCorrect(wordResult.correct())
                .withExpectedAnswer(wordResult.expectedAnswer())
                .withGivenAnswer(wordResult.givenAnswer())
                .withQuestion(wordResult.question())
                .withTranslationId(wordResult.translationId())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTranslationId() {
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
        private String translationId;
        private String question;
        private String expectedAnswer;
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

        public WordResultResponse build() {
            return new WordResultResponse(this);
        }
    }
}

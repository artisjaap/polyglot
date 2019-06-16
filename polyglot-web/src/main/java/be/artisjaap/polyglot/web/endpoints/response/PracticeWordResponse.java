package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.PracticeWordTO;

import java.util.List;
import java.util.stream.Collectors;

public class PracticeWordResponse {
    private Boolean isReversed;
    private String translationId;
    private String question;
    private String questionLanguage;
    private String answer;
    private String anwserLanguage;
    private WordStatsResponse wordStatsTO;

    private PracticeWordResponse(Builder builder) {
        isReversed = builder.isReversed;
        translationId = builder.translationId;
        question = builder.question;
        questionLanguage = builder.questionLanguage;
        answer = builder.answer;
        anwserLanguage = builder.anwserLanguage;
        wordStatsTO = builder.wordStatsTO;
    }

    public static PracticeWordResponse from(PracticeWordTO practiceWordTO) {
        return PracticeWordResponse.newBuilder()
                .withAnswer(practiceWordTO.answer())
                .withAnwserLanguage(practiceWordTO.anwserLanguage())
                .withIsReversed(practiceWordTO.reversed())
                .withQuestion(practiceWordTO.question())
                .withQuestionLanguage(practiceWordTO.questionLanguage())
                .withTranslationId(practiceWordTO.translationId())
                .withWordStatsTO(WordStatsResponse.from(practiceWordTO.wordStatsTO()))
                .build();
    }

    public static List<PracticeWordResponse> from(List<PracticeWordTO> practiceWords){
        return practiceWords.stream().map(PracticeWordResponse::from).collect(Collectors.toList());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Boolean getReversed() {
        return isReversed;
    }

    public String getTranslationId() {
        return translationId;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionLanguage() {
        return questionLanguage;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnwserLanguage() {
        return anwserLanguage;
    }

    public WordStatsResponse getWordStatsTO() {
        return wordStatsTO;
    }

    public static final class Builder {
        private Boolean isReversed;
        private String translationId;
        private String question;
        private String questionLanguage;
        private String answer;
        private String anwserLanguage;
        private WordStatsResponse wordStatsTO;

        private Builder() {
        }

        public Builder withIsReversed(Boolean val) {
            isReversed = val;
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

        public Builder withQuestionLanguage(String val) {
            questionLanguage = val;
            return this;
        }

        public Builder withAnswer(String val) {
            answer = val;
            return this;
        }

        public Builder withAnwserLanguage(String val) {
            anwserLanguage = val;
            return this;
        }

        public Builder withWordStatsTO(WordStatsResponse val) {
            wordStatsTO = val;
            return this;
        }

        public PracticeWordResponse build() {
            return new PracticeWordResponse(this);
        }
    }
}


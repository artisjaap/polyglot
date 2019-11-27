package be.artisjaap.polyglot.web.endpoints.old.request;

public class WordSolutionRequest {
    private String translationId;
    private String answerLanguage;
    private String answer;
    private String question;

    private WordSolutionRequest(Builder builder) {
        translationId = builder.translationId;
        answerLanguage = builder.answerLanguage;
        answer = builder.answer;
        question = builder.question;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTranslationId() {
        return translationId;
    }

    public String getAnswerLanguage() {
        return answerLanguage;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public static final class Builder {
        private String translationId;
        private String answerLanguage;
        private String answer;
        private String question;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withAnswerLanguage(String val) {
            answerLanguage = val;
            return this;
        }

        public Builder withAnswer(String val) {
            answer = val;
            return this;
        }

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public WordSolutionRequest build() {
            return new WordSolutionRequest(this);
        }
    }
}

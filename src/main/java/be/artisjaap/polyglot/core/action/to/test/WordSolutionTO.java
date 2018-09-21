package be.artisjaap.polyglot.core.action.to.test;

public class WordSolutionTO {
    private String translationId;
    private String answerLanguage;
    private String answer;
    private String question;

    private WordSolutionTO(Builder builder) {
        translationId = builder.translationId;
        answerLanguage = builder.answerLanguage;
        answer = builder.answer;
        question = builder.question;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String translationId() {
        return translationId;
    }

    public String answerLanguage() {
        return answerLanguage;
    }

    public String answer() {
        return answer;
    }

    public String question() {
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

        public WordSolutionTO build() {
            return new WordSolutionTO(this);
        }
    }
}

package be.artisjaap.polyglot.core.action.to;


public class PracticeWordTO {
    private Boolean isReversed;
    private String translationId;
    private String question;
    private String questionLanguage;
    private String answer;
    private String anwserLanguage;
    private WordStatsTO wordStatsTO;

    public Boolean reversed() {
        return isReversed;
    }

    public String translationId() {
        return translationId;
    }

    public String question() {
        return question;
    }

    public String questionLanguage() {
        return questionLanguage;
    }

    public String anwserLanguage() {
        return anwserLanguage;
    }

    private PracticeWordTO(Builder builder) {
        isReversed = builder.isReversed;
        translationId = builder.translationId;
        question = builder.question;
        questionLanguage = builder.questionLanguage;
        anwserLanguage = builder.anwserLanguage;
        wordStatsTO = builder.wordStatsTO;
        answer = builder.answer;
    }

    public WordStatsTO wordStatsTO() {
        return wordStatsTO;
    }

    public String answer() {
        return answer;
    }

    @Override
    public String toString() {
        return "PracticeWordTO{" +
                "isReversed=" + isReversed +
                ", translationId='" + translationId + '\'' +
                ", question='" + question + '\'' +
                ", questionLanguage='" + questionLanguage + '\'' +
                ", answer='" + answer + '\'' +
                ", anwserLanguage='" + anwserLanguage + '\'' +
                ", wordStatsTO=" + wordStatsTO +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean isReversed;
        private String translationId;
        private String question;
        private String answer;
        private String questionLanguage;
        private String anwserLanguage;
        private WordStatsTO wordStatsTO;

        private Builder() {
        }

        public Builder withTranslationId(String translationId) {
            this.translationId = translationId;
            return this;
        }

        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder withQuestionLanguage(String questionLanguage) {
            this.questionLanguage = questionLanguage;
            return this;
        }

        public Builder withAnwserLanguage(String anwserLanguage) {
            this.anwserLanguage = anwserLanguage;
            return this;
        }

        public Builder withWordStatsTO(WordStatsTO wordStatsTO) {
            this.wordStatsTO = wordStatsTO;
            return this;
        }

        public Builder withIsReversed(Boolean isReversed) {
            this.isReversed = isReversed;
            return this;
        }


        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }


        public PracticeWordTO build() {
            return new PracticeWordTO(this);
        }
    }
}

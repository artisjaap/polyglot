package be.artisjaap.polyglot.core.action.to;

public class LessonTranslationPairTO {
    private Boolean isReverse;
    private String translationId;
    private String languageFrom;
    private String languageTo;
    private String question;
    private String solution;

    private LessonTranslationPairTO(Builder builder) {
        translationId = builder.translationId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        question = builder.question;
        solution = builder.solution;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String translationId() {
        return translationId;
    }

    public String languageFrom() {
        return languageFrom;
    }

    public String languageTo() {
        return languageTo;
    }

    public String question() {
        return question;
    }

    public String solution() {
        return solution;
    }

    public Boolean isReverse() {
        return isReverse;
    }

    public static final class Builder {
        private Boolean isReverse;
        private String translationId;
        private String languageFrom;
        private String languageTo;
        private String question;
        private String solution;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withLanguageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(String val) {
            languageTo = val;
            return this;
        }

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public Builder withSolution(String val) {
            solution = val;
            return this;
        }

        public Builder withIsReverse(Boolean val) {
            isReverse = val;
            return this;
        }

        public LessonTranslationPairTO build() {
            return new LessonTranslationPairTO(this);
        }
    }
}

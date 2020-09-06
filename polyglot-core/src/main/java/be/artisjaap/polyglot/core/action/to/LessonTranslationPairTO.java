package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class LessonTranslationPairTO {
    private Boolean isReverse;
    private String translationId;
    private String languageFrom;
    private String languageTo;
    private String question;
    private Set<String> questions;
    private Set<String> solutions;


    private LessonTranslationPairTO(Builder builder) {
        isReverse = builder.isReverse;
        translationId = builder.translationId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        question = builder.question;
        solutions = builder.solutions;
        questions = builder.questions;
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

    public Set<String> questions() {
        return questions;
    }

    public Set<String> solutions() {
        return solutions;
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
        private Set<String> solutions;
        private Set<String> questions;

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

        public Builder withQuestions(Set<String> val) {
            questions = val;
            return this;
        }

        public Builder withSolutions(Set<String> val) {
            solutions = val;
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

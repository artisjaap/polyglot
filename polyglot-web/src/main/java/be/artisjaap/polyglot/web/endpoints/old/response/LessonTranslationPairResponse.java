package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LessonTranslationPairResponse {

    private Boolean isReverse;
    private String languageFrom;
    private String languageTo;
    private Set<String> question;
    private Set<String> solution;
    private String translationId;


    private LessonTranslationPairResponse(Builder builder) {
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        question = builder.question;
        solution = builder.solution;
        translationId = builder.translationId;
        isReverse = builder.isReverse;
    }
    public static List<LessonTranslationPairResponse> from(List<LessonTranslationPairTO> to) {
        return to.stream().map(LessonTranslationPairResponse::from).collect(Collectors.toList());
    }
    public static LessonTranslationPairResponse from(LessonTranslationPairTO to){
        return newBuilder()
                .withLanguageFrom(to.languageFrom())
                .withLanguageTo(to.languageTo())
                .withQuestion(Set.of(to.question()))
                .withSolution(to.solutions())
                .withTranslationId(to.translationId())
                .withIsReverse(to.isReverse())
            .build();

    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public Set<String> getQuestion() {
        return question;
    }

    public Set<String> getSolution() {
        return solution;
    }

    public String getTranslationId() {
        return translationId;
    }

    public Boolean getIsReverse() {
        return isReverse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean isReverse;
        private String languageFrom;
        private String languageTo;
        private Set<String> question;
        private Set<String> solution;
        private String translationId;

        private Builder() {
        }

        public Builder withLanguageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(String val) {
            languageTo = val;
            return this;
        }

        public Builder withQuestion(Set<String> val) {
            question = val;
            return this;
        }

        public Builder withSolution(Set<String> val) {
            solution = val;
            return this;
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withIsReverse(Boolean val) {
            isReverse = val;
            return this;
        }

        public LessonTranslationPairResponse build() {
            return new LessonTranslationPairResponse(this);
        }
    }
}

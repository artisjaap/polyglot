package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;

import java.util.List;
import java.util.stream.Collectors;

public class LessonTranslationPairResponse {

    private String languageFrom;
    private String languageTo;
    private String question;
    private String solution;
    private String translationId;


    private LessonTranslationPairResponse(Builder builder) {
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        question = builder.question;
        solution = builder.solution;
        translationId = builder.translationId;
    }
    public static List<LessonTranslationPairResponse> from(List<LessonTranslationPairTO> to) {
        return to.stream().map(LessonTranslationPairResponse::from).collect(Collectors.toList());
    }
    public static LessonTranslationPairResponse from(LessonTranslationPairTO to){
        return newBuilder()
                .withLanguageFrom(to.languageFrom())
                .withLanguageTo(to.languageTo())
                .withQuestion(to.question())
                .withSolution(to.solution())
                .withTranslationId(to.translationId())
            .build();

    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public String getQuestion() {
        return question;
    }

    public String getSolution() {
        return solution;
    }

    public String getTranslationId() {
        return translationId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String languageFrom;
        private String languageTo;
        private String question;
        private String solution;
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

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public Builder withSolution(String val) {
            solution = val;
            return this;
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public LessonTranslationPairResponse build() {
            return new LessonTranslationPairResponse(this);
        }
    }
}

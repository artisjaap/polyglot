package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTO;

import java.util.List;

public class LessonResponse {
    private String languagePairId;
    private String name;
    private String userId;
    private List<LessonTranslationPairResponse> translations;

    private LessonResponse(Builder builder) {
        languagePairId = builder.languagePairId;
        name = builder.name;
        userId = builder.userId;
        translations = builder.translations;
    }

    public static LessonResponse from(LessonTO lessonTO){
        return newBuilder().withLanguagePairId(lessonTO.languagePairId())
                .withName(lessonTO.name())
                .withUserId(lessonTO.userId())
                .withTranslations(LessonTranslationPairResponse.from(lessonTO.translations()))
                .build();


    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public List<LessonTranslationPairResponse> getTranslations() {
        return translations;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String languagePairId;
        private String name;
        private String userId;
        private List<LessonTranslationPairResponse> translations;

        private Builder() {
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withTranslations(List<LessonTranslationPairResponse> val) {
            translations = val;
            return this;
        }

        public LessonResponse build() {
            return new LessonResponse(this);
        }
    }
}

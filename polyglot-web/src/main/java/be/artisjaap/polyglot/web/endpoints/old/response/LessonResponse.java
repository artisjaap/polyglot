package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.LessonTO;

import java.util.List;

public class LessonResponse {
    private String id;
    private String languagePairId;
    private String name;
    private String userId;
    private List<LessonTranslationPairResponse> translations;

    private LessonResponse(Builder builder) {
        id = builder.id;
        languagePairId = builder.languagePairId;
        name = builder.name;
        userId = builder.userId;
        translations = builder.translations;
    }

    public static LessonResponse from(LessonTO lessonTO){
        return newBuilder().withLanguagePairId(lessonTO.languagePairId())
                .withId(lessonTO.id())
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

    public String getId(){
        return id;
    }

    public List<LessonTranslationPairResponse> getTranslations() {
        return translations;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
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

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public LessonResponse build() {
            return new LessonResponse(this);
        }
    }
}

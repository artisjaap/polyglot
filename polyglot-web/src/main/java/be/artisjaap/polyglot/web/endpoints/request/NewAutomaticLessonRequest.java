package be.artisjaap.polyglot.web.endpoints.request;

public class NewAutomaticLessonRequest {
    private String userId;
    private String languagePairId;
    private String lessonTitle;
    private Integer maxNumberOfWords;

    private NewAutomaticLessonRequest(){}

    private NewAutomaticLessonRequest(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        lessonTitle = builder.lessonTitle;
        maxNumberOfWords = builder.maxNumberOfWords;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getUserId() {
        return userId;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public Integer getMaxNumberOfWords() {
        return maxNumberOfWords;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String lessonTitle;
        private Integer maxNumberOfWords;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withLessonTitle(String val) {
            lessonTitle = val;
            return this;
        }

        public Builder withMaxNumberOfWords(Integer val) {
            maxNumberOfWords = val;
            return this;
        }

        public NewAutomaticLessonRequest build() {
            return new NewAutomaticLessonRequest(this);
        }
    }
}

package be.artisjaap.polyglot.web.endpoints.request;

public class NewLessonRequest {
    private String userId;
    private String languagePairId;
    private String lessonTitle;

    private NewLessonRequest(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePair;
        lessonTitle = builder.title;
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


    public static final class Builder {
        private String userId;
        private String languagePair;
        private String title;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePair(String val) {
            languagePair = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public NewLessonRequest build() {
            return new NewLessonRequest(this);
        }
    }
}

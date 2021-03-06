package be.artisjaap.polyglot.web.endpoints.old.request;

public class NewLessonRequest {
    private String languagePairId;
    private String lessonTitle;

    private NewLessonRequest(){}

    private NewLessonRequest(Builder builder) {
        languagePairId = builder.languagePair;
        lessonTitle = builder.title;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }


    public static final class Builder {
        private String languagePair;
        private String title;

        private Builder() {
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

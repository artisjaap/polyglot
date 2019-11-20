package be.artisjaap.polyglot.core.action.to;

public class NewWordForLessonTO {
    private String lessonId;
    private String languageFrom;
    private String languageTO;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTO() {
        return languageTO;
    }

    public void setLanguageTO(String languageTO) {
        this.languageTO = languageTO;
    }

    private NewWordForLessonTO(Builder builder) {
        lessonId = builder.lessonId;
        languageFrom = builder.languageFrom;
        languageTO = builder.languageTO;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String lessonId;
        private String languageFrom;
        private String languageTO;

        private Builder() {
        }

        public Builder lessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder languageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder languageTO(String val) {
            languageTO = val;
            return this;
        }

        public NewWordForLessonTO build() {
            return new NewWordForLessonTO(this);
        }
    }
}

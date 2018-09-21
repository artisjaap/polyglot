package be.artisjaap.polyglot.core.action.to;

public class NewAutomaticLessonTO {
    private String userId;
    private String languagePairId;
    private String lessonTitle;
    private Integer maxNumberOfWords;

    private NewAutomaticLessonTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        lessonTitle = builder.lessonTitle;
        maxNumberOfWords = builder.maxNumberOfWords;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String lessonTitle() {
        return lessonTitle;
    }

    public Integer maxNumberOfWords() {
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

        public NewAutomaticLessonTO build() {
            return new NewAutomaticLessonTO(this);
        }
    }
}

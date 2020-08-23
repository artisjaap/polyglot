package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class NewWordForLessonTO {
    private String lessonId;
    private Set<String> languageFrom;
    private Set<String> languageTO;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Set<String> getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(Set<String> languageFrom) {
        this.languageFrom = languageFrom;
    }

    public Set<String> getLanguageTO() {
        return languageTO;
    }

    public void setLanguageTO(Set<String> languageTO) {
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
        private Set<String> languageFrom;
        private Set<String> languageTO;

        private Builder() {
        }

        public Builder lessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder languageFrom(Set<String> val) {
            languageFrom = val;
            return this;
        }

        public Builder languageTO(Set<String> val) {
            languageTO = val;
            return this;
        }

        public NewWordForLessonTO build() {
            return new NewWordForLessonTO(this);
        }
    }
}

package be.artisjaap.polyglot.web.endpoints.old.request;

import java.util.Set;

public class UpdateTranslationRequest {

    private String translationId;
    private Set<String> languageFrom;
    private Set<String> languageTo;

    private UpdateTranslationRequest(Builder builder) {
        translationId = builder.translationId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public Set<String> getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(Set<String> languageFrom) {
        this.languageFrom = languageFrom;
    }

    public Set<String> getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(Set<String> languageTo) {
        this.languageTo = languageTo;
    }

    public static final class Builder {
        private String translationId;
        private Set<String> languageFrom;
        private Set<String> languageTo;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withLanguageFrom(Set<String> val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(Set<String> val) {
            languageTo = val;
            return this;
        }

        public UpdateTranslationRequest build() {
            return new UpdateTranslationRequest(this);
        }
    }
}

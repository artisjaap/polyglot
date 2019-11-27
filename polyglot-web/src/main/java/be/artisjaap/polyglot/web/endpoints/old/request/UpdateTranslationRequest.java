package be.artisjaap.polyglot.web.endpoints.old.request;

public class UpdateTranslationRequest {

    private String translationId;
    private String languageFrom;
    private String languageTo;

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

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }

    public static final class Builder {
        private String translationId;
        private String languageFrom;
        private String languageTo;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withLanguageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(String val) {
            languageTo = val;
            return this;
        }

        public UpdateTranslationRequest build() {
            return new UpdateTranslationRequest(this);
        }
    }
}

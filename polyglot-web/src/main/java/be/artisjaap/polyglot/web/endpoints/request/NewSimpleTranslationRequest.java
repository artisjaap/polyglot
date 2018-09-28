package be.artisjaap.polyglot.web.endpoints.request;

public class NewSimpleTranslationRequest {
    private String languageFrom;
    private String languageTO;

    private NewSimpleTranslationRequest(){}

    private NewSimpleTranslationRequest(Builder builder) {
        setLanguageFrom(builder.languageFrom);
        setLanguageTO(builder.languageTO);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private String languageFrom;
        private String languageTO;

        private Builder() {
        }

        public Builder withLanguageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTO(String val) {
            languageTO = val;
            return this;
        }

        public NewSimpleTranslationRequest build() {
            return new NewSimpleTranslationRequest(this);
        }
    }
}

package be.artisjaap.polyglot.web.endpoints.request;

public class LanguagePairRequest {

    private String userId;
    private String languageFrom;
    private String languageTo;

    public LanguagePairRequest() {
    }

    private LanguagePairRequest(Builder builder) {
        userId = builder.userId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        private String userId;
        private String languageFrom;
        private String languageTo;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
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

        public LanguagePairRequest build() {
            return new LanguagePairRequest(this);
        }

    }
}

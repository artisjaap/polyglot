package be.artisjaap.polyglot.core.action.to;

public class NewLanguagePairTO {
    private String userId;
    private String languageFrom;
    private String languageTo;

    public String userId() {
        return userId;
    }

    public String languageFrom() {
        return languageFrom;
    }

    public String languageTo() {
        return languageTo;
    }

    private NewLanguagePairTO(Builder builder) {
        userId = builder.userId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String languageFrom;
        private String languageTo;

        private Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withLanguageFrom(String languageFrom) {
            this.languageFrom = languageFrom;
            return this;
        }

        public Builder withLanguageTo(String languageTo) {
            this.languageTo = languageTo;
            return this;
        }

        public NewLanguagePairTO build() {
            return new NewLanguagePairTO(this);
        }
    }
}

package be.artisjaap.polyglot.core.action.to;

public class NewSimpleTranslationPairTO {
    private String languageFrom;
    private String languageTO;

    public String languageFrom() {
        return languageFrom;
    }

    public String languageTO() {
        return languageTO;
    }

    private NewSimpleTranslationPairTO(Builder builder) {
        languageFrom = builder.languageFrom;
        languageTO = builder.languageTO;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String languageFrom;
        private String languageTO;

        private Builder() {
        }

        public Builder withLanguageFrom(String languageFrom) {
            this.languageFrom = languageFrom;
            return this;
        }

        public Builder withLanguageTO(String languageTO) {
            this.languageTO = languageTO;
            return this;
        }

        public NewSimpleTranslationPairTO build() {
            return new NewSimpleTranslationPairTO(this);
        }
    }
}

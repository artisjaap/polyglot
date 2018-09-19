package be.artisjaap.polyglot.core.action.to;

public class NewTranslationPairTO {
    private String languageA;
    private String languageB;

    private NewTranslationPairTO(Builder builder) {
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String languageA() {
        return languageA;
    }

    public String languageB() {
        return languageB;
    }


    public static final class Builder {
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withLanguageA(String languageA) {
            this.languageA = languageA;
            return this;
        }

        public Builder withLanguageB(String languageB) {
            this.languageB = languageB;
            return this;
        }

        public NewTranslationPairTO build() {
            return new NewTranslationPairTO(this);
        }
    }
}

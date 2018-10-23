package be.artisjaap.polyglot.core.action.to;

public class TranslationTO {
    private String languagePairId;
    private String languageA;
    private String languageB;

    public String languagePairId() {
        return languagePairId;
    }

    public String languageA() {
        return languageA;
    }

    public String languageB() {
        return languageB;
    }

    private TranslationTO(Builder builder) {
        languagePairId = builder.languagePairId;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String languagePairId;
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withLanguageA(String val) {
            languageA = val;
            return this;
        }

        public Builder withLanguageB(String val) {
            languageB = val;
            return this;
        }

        public TranslationTO build() {
            return new TranslationTO(this);
        }
    }
}

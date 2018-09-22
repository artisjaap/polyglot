package be.artisjaap.polyglot.core.action.to;

public class TranslationPairTO {
    private String id;
    private String languageA;
    private String languageB;

    public String id() {
        return id;
    }

    public String languageA() {
        return languageA;
    }

    public String languageB() {
        return languageB;
    }

    private TranslationPairTO(Builder builder) {
        id = builder.id;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String id;
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLanguageA(String languageA) {
            this.languageA = languageA;
            return this;
        }

        public Builder withLanguageB(String languageB) {
            this.languageB = languageB;
            return this;
        }

        public TranslationPairTO build() {
            return new TranslationPairTO(this);
        }
    }
}

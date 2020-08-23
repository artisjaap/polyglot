package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class TranslationPairTO {
    private String id;
    private Set<String> languageA;
    private Set<String> languageB;

    public String id() {
        return id;
    }

    public Set<String> languageA() {
        return languageA;
    }

    public Set<String> languageB() {
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
        private Set<String> languageA;
        private Set<String> languageB;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLanguageA(Set<String> languageA) {
            this.languageA = languageA;
            return this;
        }

        public Builder withLanguageB(Set<String> languageB) {
            this.languageB = languageB;
            return this;
        }

        public TranslationPairTO build() {
            return new TranslationPairTO(this);
        }
    }
}

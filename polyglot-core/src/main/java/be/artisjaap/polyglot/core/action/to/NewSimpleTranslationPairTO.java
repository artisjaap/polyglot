package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class NewSimpleTranslationPairTO {
    private Set<String> languageFrom;
    private Set<String> languageTO;

    public Set<String> languageFrom() {
        return languageFrom;
    }

    public Set<String> languageTO() {
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
        private Set<String> languageFrom;
        private Set<String> languageTO;

        private Builder() {
        }

        public Builder withLanguageFrom(Set<String> languageFrom) {
            this.languageFrom = languageFrom;
            return this;
        }

        public Builder withLanguageTO(Set<String> languageTO) {
            this.languageTO = languageTO;
            return this;
        }

        public NewSimpleTranslationPairTO build() {
            return new NewSimpleTranslationPairTO(this);
        }
    }
}

package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class UpdateTranslationTO {

    private String translationId;
    private Set<String> languageFrom;
    private Set<String> languageTo;

    private UpdateTranslationTO(Builder builder) {
        translationId = builder.translationId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String translationId() {
        return translationId;
    }

    public Set<String> languageFrom() {
        return languageFrom;
    }

    public Set<String> languageTo() {
        return languageTo;
    }

    public static final class Builder {
        private String translationId;
        private Set<String> languageFrom;
        private Set<String> languageTo;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withLanguageFrom(Set<String> val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(Set<String> val) {
            languageTo = val;
            return this;
        }

        public UpdateTranslationTO build() {
            return new UpdateTranslationTO(this);
        }
    }
}

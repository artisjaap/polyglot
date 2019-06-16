package be.artisjaap.polyglot.core.action.to;

public class UpdateTranslationTO {

    private String translationId;
    private String languageFrom;
    private String languageTo;

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

    public String languageFrom() {
        return languageFrom;
    }

    public String languageTo() {
        return languageTo;
    }

    public static final class Builder {
        private String translationId;
        private String languageFrom;
        private String languageTo;

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
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

        public UpdateTranslationTO build() {
            return new UpdateTranslationTO(this);
        }
    }
}

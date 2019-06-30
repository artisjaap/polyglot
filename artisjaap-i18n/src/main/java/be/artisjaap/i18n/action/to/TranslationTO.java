package be.artisjaap.i18n.action.to;

import be.artisjaap.common.model.ReferenceableTO;

public class TranslationTO extends ReferenceableTO {
    private String key;
    private String translation;
    private String languageIsoCode;
    private String bundleName;

    private TranslationTO(Builder builder) {
        buildCommon(builder);
        key = builder.key;
        translation = builder.translation;
        languageIsoCode = builder.languageIsoCode;
        bundleName = builder.bundleName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String key() {
        return key;
    }

    public String translation() {
        return translation;
    }

    public String languageIsoCode() {
        return languageIsoCode;
    }

    public String bundleName() {
        return bundleName;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String key;
        private String translation;
        private String languageIsoCode;
        private String bundleName;

        private Builder() {
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withTranslation(String translation) {
            this.translation = translation;
            return this;
        }

        public Builder withLanguageIsoCode(String languageIsoCode) {
            this.languageIsoCode = languageIsoCode;
            return this;
        }

        public Builder withBundleName(String bundleName) {
            this.bundleName = bundleName;
            return this;
        }

        public TranslationTO build() {
            return new TranslationTO(this);
        }
    }
}

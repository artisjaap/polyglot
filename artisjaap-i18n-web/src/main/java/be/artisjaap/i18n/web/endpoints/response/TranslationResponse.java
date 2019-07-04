package be.artisjaap.i18n.web.endpoints.response;

import be.artisjaap.i18n.action.to.TranslationTO;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationResponse {
    private String key;
    private String translation;
    private String languageIsoCode;
    private String bundleName;

    public static TranslationResponse from(TranslationTO translationTO) {
        return TranslationResponse.newBuilder()
                .withBundleName(translationTO.bundleName())
                .withKey(translationTO.key())
                .withLanguageIsoCode(translationTO.languageIsoCode())
                .withTranslation(translationTO.translation())
                .build();
    }

    public static List<TranslationResponse> from(List<TranslationTO> translations) {
        return translations.stream().map(TranslationResponse::from)
                .collect(Collectors.toList());
    }

    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return translation;
    }

    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    public String getBundleName() {
        return bundleName;
    }

    private TranslationResponse(Builder builder) {
        key = builder.key;
        translation = builder.translation;
        languageIsoCode = builder.languageIsoCode;
        bundleName = builder.bundleName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
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

        public TranslationResponse build() {
            return new TranslationResponse(this);
        }
    }
}

package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;

import java.util.List;

public class TranslationsForUserResponse {
    private String userId;
    private String languagePairId;
    private List<TranslationPairResponse> translations;


    private TranslationsForUserResponse(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
    }

    public static TranslationsForUserResponse from(TranslationsForUserTO translationsForUserTO) {
        return TranslationsForUserResponse.newBuilder()
                .withUserId(translationsForUserTO.userId())
                .withLanguagePairId(translationsForUserTO.languagePairId())
                .withTranslations(TranslationPairResponse.from(translationsForUserTO.translations()))
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public List<TranslationPairResponse> getTranslations() {
        return translations;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private List<TranslationPairResponse> translations;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withTranslations(List<TranslationPairResponse> val) {
            translations = val;
            return this;
        }

        public TranslationsForUserResponse build() {
            return new TranslationsForUserResponse(this);
        }
    }
}

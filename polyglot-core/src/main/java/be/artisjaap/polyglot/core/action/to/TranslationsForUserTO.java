package be.artisjaap.polyglot.core.action.to;

import java.util.List;

public class TranslationsForUserTO {
    private String userId;
    private String languagePairId;
    private List<TranslationTO> translations;

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public List<TranslationTO> translations() {
        return translations;
    }

    private TranslationsForUserTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String languagePairId;
        private List<TranslationTO> translations;

        private Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withLanguagePairId(String languagePairId) {
            this.languagePairId = languagePairId;
            return this;
        }

        public Builder withTranslations(List<TranslationTO> translations) {
            this.translations = translations;
            return this;
        }

        public TranslationsForUserTO build() {
            return new TranslationsForUserTO(this);
        }
    }
}

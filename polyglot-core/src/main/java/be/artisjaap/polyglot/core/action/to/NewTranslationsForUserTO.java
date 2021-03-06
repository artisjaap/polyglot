package be.artisjaap.polyglot.core.action.to;

import java.util.ArrayList;
import java.util.List;

public class NewTranslationsForUserTO {
    private String userId;
    private String languagePairId;
    private List<NewSimpleTranslationPairTO> translations;

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public List<NewSimpleTranslationPairTO> translations() {
        return translations;
    }

    private NewTranslationsForUserTO(Builder builder) {
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
        private List<NewSimpleTranslationPairTO> translations = new ArrayList<>();

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

        public Builder withTranslations(List<NewSimpleTranslationPairTO> translations) {
            this.translations = translations;
            return this;
        }

        public Builder addTranslation(NewSimpleTranslationPairTO translation){
            this.translations.add(translation);
            return this;
        }

        public NewTranslationsForUserTO build() {
            return new NewTranslationsForUserTO(this);
        }
    }
}

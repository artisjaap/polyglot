package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationTO;

public class TranslationResponse {
    private String languagePairId;
    private String languageA;
    private String languageB;



    private TranslationResponse(Builder builder) {
        languagePairId = builder.languagePairId;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static TranslationResponse from(TranslationTO t){
        return newBuilder()
                .withLanguageA(t.languageA())
                .withLanguageB(t.languageB())
                .withLanguagePairId(t.languagePairId())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getLanguageA() {
        return languageA;
    }

    public String getLanguageB() {
        return languageB;
    }

    public static final class Builder {
        private String languagePairId;
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withLanguageA(String val) {
            languageA = val;
            return this;
        }

        public Builder withLanguageB(String val) {
            languageB = val;
            return this;
        }

        public TranslationResponse build() {
            return new TranslationResponse(this);
        }
    }
}

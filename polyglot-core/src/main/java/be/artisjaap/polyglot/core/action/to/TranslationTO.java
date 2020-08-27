package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.model.ReferenceableTO;

import java.util.Set;


public class TranslationTO extends ReferenceableTO {

    private String languagePairId;
    private Set<String> languageA;
    private Set<String> languageB;

    public String languagePairId() {
        return languagePairId;
    }

    public Set<String> languageA() {
        return languageA;
    }

    public Set<String> languageB() {
        return languageB;
    }

    private TranslationTO(Builder builder) {
        buildCommon(builder);
        languagePairId = builder.languagePairId;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public TranslationTO switchLanguages(){
        return TranslationTO.newBuilder()
                .withLanguageA(languageB())
                .withLanguageB(languageA())
                .withLanguagePairId(languagePairId())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder> {
        private String languagePairId;
        private Set<String> languageA;
        private Set<String> languageB;

        private Builder() {
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withLanguageA(Set<String> val) {
            languageA = val;
            return this;
        }

        public Builder withLanguageB(Set<String> val) {
            languageB = val;
            return this;
        }

        public TranslationTO build() {
            return new TranslationTO(this);
        }
    }
}

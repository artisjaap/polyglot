package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;

public class LanguagePairDataSet {
    private String languageA;
    private String languageB;

    private LanguagePairDataSet(Builder builder) {
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(LanguagePairDataSet copy) {
        Builder builder = new Builder();
        builder.languageA = copy.getLanguageA();
        builder.languageB = copy.getLanguageB();
        return builder;
    }

    public String getLanguageA() {
        return languageA;
    }

    public String getLanguageB() {
        return languageB;
    }

    public static LanguagePairDataSet from(LanguagePairTO languagePairTO) {
        return LanguagePairDataSet.newBuilder()
                .withLanguageA(languagePairTO.languageFrom())
                .withLanguageB(languagePairTO.languageTo())
                .build();
    }

    public static final class Builder {
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withLanguageA(String val) {
            languageA = val;
            return this;
        }

        public Builder withLanguageB(String val) {
            languageB = val;
            return this;
        }

        public LanguagePairDataSet build() {
            return new LanguagePairDataSet(this);
        }
    }
}

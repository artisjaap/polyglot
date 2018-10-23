package be.artisjaap.polyglot.core.action.to;

public class TranslationFilterTO extends PageableTO{
    private String textFilter;
    private String languagePairId;

    public String textFilter() {
        return textFilter;
    }

    public String languagePairId() {
        return languagePairId;
    }

    private TranslationFilterTO(Builder builder) {
        buildCommon(builder);
        textFilter = builder.textFilter;
        languagePairId = builder.languagePairId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder>{
        private String textFilter;
        private String languagePairId;

        private Builder() {
        }

        public Builder withTextFilter(String val) {
            textFilter = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public TranslationFilterTO build() {
            return new TranslationFilterTO(this);
        }
    }
}

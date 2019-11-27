package be.artisjaap.polyglot.web.endpoints.old.request;

public class TranslationsFilterRequest extends PageableFilter{
    private String textFilter;
    private String languagePairId;

    private TranslationsFilterRequest(){}

    public String getTextFilter() {
        return textFilter;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    private TranslationsFilterRequest(Builder builder) {
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


        public TranslationsFilterRequest build() {
            return new TranslationsFilterRequest(this);
        }
    }
}

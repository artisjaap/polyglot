package be.artisjaap.polyglot.web.endpoints.request;

public class LessonsFilterRequest extends PageableFilter{
    private String textFilter;
    private String languagePairId;

    private LessonsFilterRequest(){}

    private LessonsFilterRequest(Builder builder) {
        buildCommon(builder);
        textFilter = builder.textFilter;
        languagePairId = builder.languagePairId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTextFilter() {
        return textFilter;
    }

    public String getLanguagePairId() {
        return languagePairId;
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

        public LessonsFilterRequest build() {
            return new LessonsFilterRequest(this);
        }
    }
}
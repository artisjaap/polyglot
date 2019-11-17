package be.artisjaap.polyglot.core.action.to;

public class LessonFilterTO extends PageableTO {
    private String textFilter;
    private String languagePairId;


    private LessonFilterTO(Builder builder) {
        buildCommon(builder);
        textFilter = builder.textFilter;
        languagePairId = builder.languagePairId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String textFilter() {
        return textFilter;
    }

    public String languagePairId() {
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

        public LessonFilterTO build() {
            return new LessonFilterTO(this);
        }
    }
}

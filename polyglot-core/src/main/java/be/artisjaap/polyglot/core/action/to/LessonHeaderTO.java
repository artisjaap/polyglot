package be.artisjaap.polyglot.core.action.to;

public class LessonHeaderTO {

    private String name;
    private String languagePairId;
    private String userId;

    private LessonHeaderTO(Builder builder) {
        name = builder.name;
        languagePairId = builder.languagePairId;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String userId() {
        return userId;
    }

    public static final class Builder {
        private String name;
        private String languagePairId;
        private String userId;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public LessonHeaderTO build() {
            return new LessonHeaderTO(this);
        }
    }
}

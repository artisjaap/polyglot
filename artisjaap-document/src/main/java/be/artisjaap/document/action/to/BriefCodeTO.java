package be.artisjaap.document.action.to;

public class BriefCodeTO {

    private final String code;
    private final String description;

    private BriefCodeTO(Builder builder) {
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static final class Builder {
        private String code;
        private String description;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BriefCodeTO build() {
            return new BriefCodeTO(this);
        }
    }
}

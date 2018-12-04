package be.artisjaap.document.action.to;

public class BriefCodeNieuwTO {
    private final String code;
    private final String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    private BriefCodeNieuwTO(Builder builder) {
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
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

        public BriefCodeNieuwTO build() {
            return new BriefCodeNieuwTO(this);
        }
    }
}

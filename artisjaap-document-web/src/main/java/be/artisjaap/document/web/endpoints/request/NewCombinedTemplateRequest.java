package be.artisjaap.document.web.endpoints.request;

public class NewCombinedTemplateRequest {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private NewCombinedTemplateRequest(Builder builder) {
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

        public NewCombinedTemplateRequest build() {
            return new NewCombinedTemplateRequest(this);
        }
    }
}

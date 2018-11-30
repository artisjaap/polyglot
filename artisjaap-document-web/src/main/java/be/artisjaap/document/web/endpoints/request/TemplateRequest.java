package be.artisjaap.document.web.endpoints.request;

public class TemplateRequest {
    private String code;
    private String language;

    private TemplateRequest(Builder builder) {
        code = builder.code;
        language = builder.language;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public static final class Builder {
        private String code;
        private String language;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        public TemplateRequest build() {
            return new TemplateRequest(this);
        }
    }
}

package be.artisjaap.document.web.endpoints.response;

import be.artisjaap.document.action.to.CombinedTemplateCodeTO;

public class CombinedTemplateCodeResponse {
    private String code;
    private String description;

    private CombinedTemplateCodeResponse(Builder builder) {
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static CombinedTemplateCodeResponse from(CombinedTemplateCodeTO combinedTemplateTO) {
        return CombinedTemplateCodeResponse.newBuilder()
                .withCode(combinedTemplateTO.getCode())
                .withDescription(combinedTemplateTO.getDescription())
                .build();
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

        public CombinedTemplateCodeResponse build() {
            return new CombinedTemplateCodeResponse(this);
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }
}

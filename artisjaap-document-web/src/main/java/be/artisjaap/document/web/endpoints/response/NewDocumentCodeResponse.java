package be.artisjaap.document.web.endpoints.response;

import be.artisjaap.document.action.to.BriefCodeTO;

public class NewDocumentCodeResponse {

    private String code;
    private String description;

    private NewDocumentCodeResponse(Builder builder) {
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static NewDocumentCodeResponse from(BriefCodeTO briefCodeTO) {
        return NewDocumentCodeResponse.newBuilder()
                .withCode(briefCodeTO.getCode())
                .withDescription(briefCodeTO.getDescription())
                .build();
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

        public NewDocumentCodeResponse build() {
            return new NewDocumentCodeResponse(this);
        }
    }
}

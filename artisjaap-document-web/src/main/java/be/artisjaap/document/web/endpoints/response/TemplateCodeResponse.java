package be.artisjaap.document.web.endpoints.response;

import be.artisjaap.document.action.to.TemplateCodeTO;

import java.util.List;
import java.util.stream.Collectors;

public class TemplateCodeResponse {
    private final String code;
    private final String description;

    private TemplateCodeResponse(Builder builder) {
        code = builder.code;
        description = builder.description;
    }

    public static TemplateCodeResponse from(TemplateCodeTO templateCodeTO) {
        return TemplateCodeResponse.newBuilder()
                .withCode(templateCodeTO.getCode())
                .withDescription(templateCodeTO.getDescription())
                .build();
    }

    public static List<TemplateCodeResponse> from(List<TemplateCodeTO> templateCodeTO) {
        return templateCodeTO.stream().map(TemplateCodeResponse::from).collect(Collectors.toList());
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

        public TemplateCodeResponse build() {
            return new TemplateCodeResponse(this);
        }
    }
}

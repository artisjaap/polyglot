package be.artisjaap.document.web.endpoints.response;

import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import be.artisjaap.document.action.to.TemplateCodeTO;

import java.time.LocalDateTime;
import java.util.List;

public class CombinedTemplateResponse {
    private String language;
    private List<TemplateCodeResponse> templates;
    private String id;
    private String code;
    private Boolean active;
    private LocalDateTime createdOn;

    private CombinedTemplateResponse(Builder builder) {
        language = builder.language;
        templates = builder.templates;
        id = builder.id;
        code = builder.code;
        active = builder.active;
        createdOn = builder.createdOn;
    }

    public String getLanguage() {
        return language;
    }

    public List<TemplateCodeResponse> getTemplates() {
        return templates;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public static CombinedTemplateResponse from(GecombineerdeTemplateTO combinedTemplate) {
        return CombinedTemplateResponse.newBuilder()
                .withLanguage(combinedTemplate.taal())
                .withTemplates(TemplateCodeResponse.from(combinedTemplate.templates()))
                .withId(combinedTemplate.getId())
                .withCode(combinedTemplate.code())
                .withActive(combinedTemplate.actief())
                .withCreatedOn(combinedTemplate.aangemaaktOp())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String language;
        private List<TemplateCodeResponse> templates;
        private String id;
        private String code;
        private Boolean active;
        private LocalDateTime createdOn;

        private Builder() {
        }

        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder withTemplates(List<TemplateCodeResponse> templates) {
            this.templates = templates;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Builder withCreatedOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public CombinedTemplateResponse build() {
            return new CombinedTemplateResponse(this);
        }


    }
}

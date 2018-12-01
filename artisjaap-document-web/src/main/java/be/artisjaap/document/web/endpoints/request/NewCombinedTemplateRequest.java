package be.artisjaap.document.web.endpoints.request;

import java.util.List;

public class NewCombinedTemplateRequest {
    private String taal;
    private List<String> templates;
    private String code;

    private NewCombinedTemplateRequest(Builder builder) {
        taal = builder.taal;
        templates = builder.templates;
        code = builder.code;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getTaal() {
        return taal;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public String getCode() {
        return code;
    }


    public static final class Builder {
        private String taal;
        private List<String> templates;
        private String code;

        private Builder() {
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withTemplates(List<String> templates) {
            this.templates = templates;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public NewCombinedTemplateRequest build() {
            return new NewCombinedTemplateRequest(this);
        }
    }
}


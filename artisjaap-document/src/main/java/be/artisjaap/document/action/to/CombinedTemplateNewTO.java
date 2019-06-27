package be.artisjaap.document.action.to;

import java.util.List;

public class CombinedTemplateNewTO {
    private String taal;
    private List<String> templates;

    private String code;


    public String taal() {
        return taal;
    }

    public List<String> templates() {
        return templates;
    }

    public String code() {
        return code;
    }

    private CombinedTemplateNewTO(Builder builder) {
        taal = builder.taal;
        templates = builder.templates;
        code = builder.code;
    }

    public static Builder newBuilder() {
        return new Builder();
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

        public CombinedTemplateNewTO build() {
            return new CombinedTemplateNewTO(this);
        }
    }
}

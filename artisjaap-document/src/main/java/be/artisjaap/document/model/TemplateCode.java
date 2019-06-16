package be.artisjaap.document.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BwTemplateCode")
public class TemplateCode extends AbstractDocument{
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private TemplateCode() {
    }

    private TemplateCode(Builder builder) {
        buildCommon(builder);
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder>{
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

        public TemplateCode build() {
            return new TemplateCode(this);
        }
    }
}

package be.artisjaap.document.action.to;


public class TemplateNieuwTO {
    private final String code;
    private final String originalFilename;
    private final byte[] template;
    private final String taal;

    private TemplateNieuwTO(Builder builder) {
        code = builder.code;
        originalFilename = builder.originalFilename;
        template = builder.template;
        taal = builder.taal;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public byte[] getTemplate() {
        return template;
    }

    public String getTaal() {
        return taal;
    }

    public static final class Builder {
        private String code;
        private String originalFilename;
        private byte[] template;
        private String taal;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withOriginalFilename(String originalFilename) {
            this.originalFilename = originalFilename;
            return this;
        }

        public Builder withTemplate(byte[] template) {
            this.template = template;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public TemplateNieuwTO build() {
            return new TemplateNieuwTO(this);
        }
    }
}

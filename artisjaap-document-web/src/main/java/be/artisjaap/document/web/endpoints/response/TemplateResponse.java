package be.artisjaap.document.web.endpoints.response;

import be.artisjaap.document.action.to.TemplateTO;

import java.time.LocalDateTime;

public class TemplateResponse {
    private final String code;
    private final String originalFilename;
    private final byte[] template;
    private final String taal;
    private final String id;
    private final Boolean actief;
    private final LocalDateTime aangemaaktOp;

    private TemplateResponse(Builder builder) {
        code = builder.code;
        originalFilename = builder.originalFilename;
        template = builder.template;
        taal = builder.taal;
        id = builder.id;
        actief = builder.actief;
        aangemaaktOp = builder.aangemaaktOp;
    }

    public static TemplateResponse from(TemplateTO template) {
        return TemplateResponse.newBuilder()
                .withCode(template.getCode())
                .withOriginalFilename(template.getOriginalFilename())
                .withTemplate(template.getTemplate())
                .withTaal(template.getTaal())
                .withId(template.getId())
                .withActief(template.getActief())
                .withAangemaaktOp(template.getAangemaaktOp())
                .build();
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

    public String getId() {
        return id;
    }

    public Boolean getActief() {
        return actief;
    }

    public LocalDateTime getAangemaaktOp() {
        return aangemaaktOp;
    }

    public static final class Builder {
        private String code;
        private String originalFilename;
        private byte[] template;
        private String taal;
        private String id;
        private Boolean actief;
        private LocalDateTime aangemaaktOp;

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

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withActief(Boolean actief) {
            this.actief = actief;
            return this;
        }

        public Builder withAangemaaktOp(LocalDateTime aangemaaktOp) {
            this.aangemaaktOp = aangemaaktOp;
            return this;
        }

        public TemplateResponse build() {
            return new TemplateResponse(this);
        }
    }
}

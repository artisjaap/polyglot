package be.artisjaap.document.action.to;


import java.time.LocalDateTime;
import java.util.List;

public class GecombineerdeTemplateTO {
    private String taal;
    private List<TemplateCodeTO> templates;
    private String id;
    private String code;
    private Boolean actief;
    private final LocalDateTime aangemaaktOp;


    public String taal() {
        return taal;
    }

    public List<TemplateCodeTO> templates() {
        return templates;
    }

    public String code() {
        return code;
    }

    public String getId() {
        return id;
    }

    public Boolean actief() {
        return actief;
    }

    public LocalDateTime aangemaaktOp() {
        return aangemaaktOp;
    }

    private GecombineerdeTemplateTO(Builder builder) {
        taal = builder.taal;
        templates = builder.templates;
        code = builder.code;
        id = builder.id;
        actief = builder.actief;
        aangemaaktOp = builder.aangemaaktOp;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String taal;
        private List<TemplateCodeTO> templates;
        private String code;
        private String id;
        private Boolean actief;
        private LocalDateTime aangemaaktOp;

        private Builder() {
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withTemplates(List<TemplateCodeTO> templates) {
            this.templates = templates;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
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

        public GecombineerdeTemplateTO build() {
            return new GecombineerdeTemplateTO(this);
        }
    }
}

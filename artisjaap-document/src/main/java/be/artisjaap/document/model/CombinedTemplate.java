package be.artisjaap.document.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "BwGecombineerdeTemplate")
public class CombinedTemplate extends AbstractDocument {

    private String taal;
    private List<String> templates;
    private String code;
    private Boolean actief;

    public String getTaal() {
        return taal;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public String getCode() {
        return code;
    }

    public Boolean getActief() {
        return actief;
    }

    private CombinedTemplate(){}

    private CombinedTemplate(Builder builder) {
        buildCommon(builder);
        taal = builder.taal;
        templates = builder.templates;
        code = builder.code;
        actief = builder.actief;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void deactivate() {
        actief = false;
    }

    public void activeer() {
        actief = true;
    }

    public static final class Builder extends AbstractBuilder<Builder>{
        private String taal;
        private List<String> templates;
        private String code;
        private Boolean actief = Boolean.FALSE;

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

        public Builder withNaam(String code) {
            this.code = code;
            return this;
        }

        public Builder withActief(String actief) {
            this.code = actief;
            return this;
        }

        public CombinedTemplate build() {
            return new CombinedTemplate(this);
        }
    }
}

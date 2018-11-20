package be.artisjaap.document.model;

import be.artisjaap.document.utils.DocType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document(collection = "BwTemplate")
public class Template extends AbstractDocument {

    private String code;
    private byte[] template;
    private Map<String, List<String>> datasets;

    private Boolean actief;
    private DocType doctype;
    private String originalFilename;
    private String taal;

    public String getCode() {
        return code;
    }

    public byte[] getTemplate() {
        return template;
    }

    public Map<String, List<String>> getDatasets() {
        return datasets;
    }

    public Boolean getActief() {
        return actief;
    }

    public DocType getDoctype() {
        return doctype;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getTaal() {
        return taal;
    }

    public void desactiveer() {
        actief = false;
    }
    public void activeer() {
        actief = true;
    }

    public Set<String > datasetNames() {
        return datasets.keySet();
    }


    protected Template() {}

    private Template(Builder builder) {
        buildCommon(builder);
        code = builder.code;
        template = builder.template;
        datasets = builder.datasets;
        actief = builder.actief;
        doctype = builder.doctype;
        originalFilename = builder.originalFilename;
        taal = builder.taal;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String code;
        private byte[] template;
        private Map<String, List<String>> datasets;
        private Boolean actief;
        private DocType doctype;
        private String originalFilename;
        private String taal;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withTemplate(byte[] template) {
            this.template = template;
            return this;
        }

        public Builder withDatasets(Map<String, List<String>> datasets) {
            this.datasets = datasets;
            return this;
        }

        public Builder withActief(Boolean actief) {
            this.actief = actief;
            return this;
        }

        public Builder withDoctype(DocType doctype) {
            this.doctype = doctype;
            return this;
        }

        public Builder withOriginalFilename(String originalFilename) {
            this.originalFilename = originalFilename;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Template build() {
            return new Template(this);
        }
    }
}

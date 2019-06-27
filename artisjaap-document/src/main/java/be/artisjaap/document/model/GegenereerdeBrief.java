package be.artisjaap.document.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection = "BwGegenereerdeBrief")
public class GegenereerdeBrief extends AbstractDocument {
    private String taal;
    private DocumentLocation documentLocation;
    private String briefCode;
    private ObjectId briefRef;
    private Set<ObjectId> gebruikteTemplates;
    private Map<String, Object> datasets;

    public String getTaal() {
        return taal;
    }

    public DocumentLocation getDocumentLocation() {
        return documentLocation;
    }

    public String getBriefCode() {
        return briefCode;
    }

    public ObjectId getBriefRef() {
        return briefRef;
    }

    public Set<ObjectId> getGebruikteTemplates() {
        return gebruikteTemplates;
    }

    public Map<String, Object> getDatasets() {
        return datasets;
    }

    protected GegenereerdeBrief() {

    }

    private GegenereerdeBrief(Builder builder) {
        documentLocation = builder.documentLocation;
        briefRef = builder.briefRef;
        gebruikteTemplates = builder.gebruikteTemplates;
        datasets = builder.datasets;
        taal = builder.taal;
        briefCode = builder.briefCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private DocumentLocation documentLocation;
        private ObjectId briefRef;
        private Set<ObjectId> gebruikteTemplates;
        private Map<String, Object> datasets;
        private String taal;
        private String briefCode;

        private Builder() {
        }


        public Builder withBriefLocatie(DocumentLocation documentLocation) {
            this.documentLocation = documentLocation;
            return this;
        }

        public Builder withBriefRef(ObjectId briefRef) {
            this.briefRef = briefRef;
            return this;
        }

        public Builder withGebruikteTemplates(Set<ObjectId> gebruikteTemplates) {
            this.gebruikteTemplates = gebruikteTemplates;
            return this;
        }

        public Builder withDatasets(Map<String, Object> datasets) {
            this.datasets = datasets;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withBriefcode(String briefCode) {
            this.briefCode = briefCode;
            return this;
        }

        public GegenereerdeBrief build() {
            return new GegenereerdeBrief(this);
        }
    }
}

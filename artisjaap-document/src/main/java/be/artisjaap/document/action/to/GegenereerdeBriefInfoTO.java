package be.artisjaap.document.action.to;

import be.artisjaap.document.api.brieflocatie.BriefLocatieType;

public class GegenereerdeBriefInfoTO {
    private String id;
    private String taal;
    private String filename;
    private BriefLocatieType briefLocatieType;
    private String briefCode;

    private GegenereerdeBriefInfoTO(Builder builder) {
        id = builder.id;
        taal = builder.taal;
        filename = builder.filename;
        briefLocatieType = builder.briefLocatieType;
        briefCode = builder.briefCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTaal() {
        return taal;
    }

    public String getFilename() {
        return filename;
    }

    public BriefLocatieType getBriefLocatieType() {
        return briefLocatieType;
    }

    public String getBriefCode() {
        return briefCode;
    }


    public static final class Builder {
        private String id;
        private String taal;
        private String filename;
        private BriefLocatieType briefLocatieType;
        private String briefCode;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder taal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder briefLocatieType(BriefLocatieType briefLocatieType) {
            this.briefLocatieType = briefLocatieType;
            return this;
        }

        public Builder briefCode(String briefCode) {
            this.briefCode = briefCode;
            return this;
        }

        public GegenereerdeBriefInfoTO build() {
            return new GegenereerdeBriefInfoTO(this);
        }
    }
}

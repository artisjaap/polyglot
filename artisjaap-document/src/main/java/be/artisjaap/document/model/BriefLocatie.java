package be.artisjaap.document.model;

import be.artisjaap.document.api.brieflocatie.BriefLocatieType;

public class BriefLocatie {
    private String gegenereerdeBestandsnaam;
    private String path;
    private BriefLocatieType type;
    private byte[] document;

    public String getPath() {
        return path;
    }

    public BriefLocatieType getType() {
        return type;
    }

    public byte[] getDocument() {
        return document;
    }

    public String getGegenereerdeBestandsnaam() {
        return gegenereerdeBestandsnaam;
    }

    private BriefLocatie() {

    }


    private BriefLocatie(Builder builder) {
        path = builder.path;
        type = builder.type;
        document = builder.document;
        gegenereerdeBestandsnaam = builder.gegenereerdeBestandsnaam;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String path;
        private BriefLocatieType type = BriefLocatieType.IN_DB;
        private byte[] document;
        private String gegenereerdeBestandsnaam;

        private Builder() {
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withType(BriefLocatieType type) {
            this.type = type;
            return this;
        }

        public Builder withDocument(byte[] document) {
            this.document = document;
            return this;
        }

        public Builder withGegenereerdeBestandsnaam(String bestandsnaam) {
            this.gegenereerdeBestandsnaam = bestandsnaam;
            return this;
        }

        public BriefLocatie build() {
            return new BriefLocatie(this);
        }
    }
}

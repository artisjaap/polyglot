package be.artisjaap.document.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BwBriefCode")
public class BriefCode extends AbstractDocument{
    private String code;
    private String omschrijvingNl;
    private String omschrijvingFr;

    private BriefCode() {

    }


    public String getCode() {
        return code;
    }

    public String getOmschrijvingNl() {
        return omschrijvingNl;
    }

    public String getOmschrijvingFr() {
        return omschrijvingFr;
    }

    private BriefCode(Builder builder) {
        buildCommon(builder);
        code = builder.code;
        omschrijvingNl = builder.omschrijvingNl;
        omschrijvingFr = builder.omschrijvingFr;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder> {
        private String code;
        private String omschrijvingNl;
        private String omschrijvingFr;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withOmschrijvingNl(String omschrijvingNl) {
            this.omschrijvingNl = omschrijvingNl;
            return this;
        }

        public Builder withOmschrijvingFr(String omschrijvingFr) {
            this.omschrijvingFr = omschrijvingFr;
            return this;
        }

        public BriefCode build() {
            return new BriefCode(this);
        }
    }
}

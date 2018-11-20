package be.artisjaap.document.action.to;

public class BriefCodeTO {

    private final String code;
    private final String omschrijvingNl;
    private final String omschrijvingFr;
    private final Boolean gevalideerdNl;
    private final Boolean gevalideerdFr;

    private BriefCodeTO(Builder builder) {
        code = builder.code;
        omschrijvingNl = builder.omschrijvingNl;
        omschrijvingFr = builder.omschrijvingFr;
        gevalideerdNl = builder.gevalideerdNl;
        gevalideerdFr = builder.gevalideerdFr;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public Boolean getGevalideerdNl() {
        return gevalideerdNl;
    }

    public Boolean getGevalideerdFr() {
        return gevalideerdFr;
    }

    public static final class Builder {
        private String code;
        private String omschrijvingNl;
        private String omschrijvingFr;
        private Boolean gevalideerdNl;
        private Boolean gevalideerdFr;

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

        public Builder withGevalideerdNl(Boolean gevalideerdNl) {
            this.gevalideerdNl = gevalideerdNl;
            return this;
        }

        public Builder withGevalideerdFr(Boolean gevalideerdFr) {
            this.gevalideerdFr = gevalideerdFr;
            return this;
        }
        public BriefCodeTO build() {
            return new BriefCodeTO(this);
        }
    }
}

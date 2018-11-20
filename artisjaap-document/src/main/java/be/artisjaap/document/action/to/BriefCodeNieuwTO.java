package be.artisjaap.document.action.to;

public class BriefCodeNieuwTO {
    private final String code;
    private final String omschrijvingNl;
    private final String omschrijvingFr;

    public String getCode() {
        return code;
    }

    public String getOmschrijvingNl() {
        return omschrijvingNl;
    }

    public String getOmschrijvingFr() {
        return omschrijvingFr;
    }

    private BriefCodeNieuwTO(Builder builder) {
        code = builder.code;
        omschrijvingNl = builder.omschrijvingNl;
        omschrijvingFr = builder.omschrijvingFr;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
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

        public BriefCodeNieuwTO build() {
            return new BriefCodeNieuwTO(this);
        }
    }
}

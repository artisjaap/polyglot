package be.artisjaap.document.api;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.to.BriefTO;

import java.time.LocalDateTime;


public class BriefMetaDataset {

    private LocalDateTime nu;
    private String taal;
    private String referentie;
    private String briefCode;

    private BriefMetaDataset(){

    }

    private BriefMetaDataset(Builder builder) {
        nu = builder.nu;
        taal = builder.taal;
        referentie = builder.referentie;
        briefCode = builder.briefCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getAanmaakdatum() {
        return LocalDateUtils.format(nu, LocalDateUtils.DATE_FORMATTED_DDMMYYYY_MET_SLASHES);
    }

    public String getAanmaakTimestamp() {
        return LocalDateUtils.format(nu, LocalDateUtils.DATE_FORMAT_YYYYMMDDHHMMSS);
    }

    public String getBriefCode() {
        return briefCode;
    }

    public String getIsoCode(){
        return taal;
    }

    public String getReferentie() {
        return referentie;
    }


    public static BriefMetaDataset fromBrief(BriefTO briefTO) {
        return newBuilder().withTaal(briefTO.getTaal())
                .withReferentie(briefTO.getReferentie())
                .withBriefCode(briefTO.getNaam())
                .build();

    }


    public static final class Builder {
        private LocalDateTime nu = LocalDateUtils.now();
        private String taal;
        private String referentie;
        private String briefCode;

        private Builder() {
        }

        public Builder withNu(LocalDateTime nu) {
            this.nu = nu;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withReferentie(String referentie) {
            this.referentie = referentie;
            return this;
        }

        public Builder withBriefCode(String briefCode) {
            this.briefCode = briefCode;
            return this;
        }

        public BriefMetaDataset build() {
            return new BriefMetaDataset(this);
        }
    }
}

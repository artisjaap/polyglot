package be.artisjaap.document.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BwBriefCode")
public class BriefCode extends AbstractDocument{
    private String code;
    private String description;

    private BriefCode() {

    }


    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private BriefCode(Builder builder) {
        buildCommon(builder);
        code = builder.code;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder> {
        private String code;
        private String description;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withDescription(String omschrijvingNl) {
            this.description = omschrijvingNl;
            return this;
        }

        public BriefCode build() {
            return new BriefCode(this);
        }
    }
}

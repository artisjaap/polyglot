package be.artisjaap.document.action.to;


import java.util.List;

public class BriefNieuwTO {
    private String code;
    private List<String> templates;
    private String taal;


    public String getCode() {
        return code;
    }

    public List<String> getPaginas() {
        return templates;
    }

    public String getTaal() {
        return taal;
    }

    private BriefNieuwTO(Builder builder) {
        code = builder.code;
        templates = builder.templates;
        taal = builder.taal;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String code;
        private List<String> templates;
        private String taal;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }


        public Builder withTemplates(List<String> templates) {
            this.templates = templates;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public BriefNieuwTO build() {
            return new BriefNieuwTO(this);
        }
    }
}

package be.artisjaap.document.action.to;


import be.artisjaap.document.model.PaginaType;

public class PaginaTO {

    private PaginaType type;
    private String code;

    private PaginaTO(Builder builder) {
        type = builder.type;
        code = builder.code;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PaginaType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }


    public static final class Builder {
        private PaginaType type;
        private String code;

        private Builder() {
        }

        public Builder withType(PaginaType type) {
            this.type = type;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public PaginaTO build() {
            return new PaginaTO(this);
        }
    }
}

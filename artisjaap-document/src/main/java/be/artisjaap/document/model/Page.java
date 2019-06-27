package be.artisjaap.document.model;

public class Page {

    private PaginaType paginaType;
    private String code;

    private Page(){}

    private Page(Builder builder) {
        paginaType = builder.paginaType;
        code = builder.code;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PaginaType getPaginaType() {
        return paginaType;
    }

    public String getCode() {
        return code;
    }


    public static final class Builder {
        private PaginaType paginaType;
        private String code;

        private Builder() {
        }

        public Builder withPaginaType(PaginaType paginaType) {
            this.paginaType = paginaType;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Page build() {
            return new Page(this);
        }
    }
}

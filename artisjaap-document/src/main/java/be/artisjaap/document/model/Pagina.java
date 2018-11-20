package be.artisjaap.document.model;

public class Pagina {

    private PaginaType paginaType;
    private String code;

    private Pagina(){}

    private Pagina(Builder builder) {
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

        public Pagina build() {
            return new Pagina(this);
        }
    }
}

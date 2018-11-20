package be.artisjaap.document.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "BwBrief")
public class Brief extends AbstractDocument{

    private List<Pagina> paginas;
    private String taal;
    private String code;
    private Boolean actief;
    private String type;

    public String getCode() {
        return code;
    }

    private Brief(){}

    private Brief(Builder builder) {
        buildCommon(builder);
        paginas = builder.paginas;
        taal = builder.taal;
        code = builder.code;
        actief = builder.actief;
        type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Pagina> getPaginas() {
        return paginas;
    }

    public String getTaal() {
        return taal;
    }

    public void desactiveer() {
        actief = false;
    }

    public void activeer() {
        actief = true;
    }

    public String getType() {
        return type;
    }

    public Boolean getActief() {
        return actief;
    }

    public static final class Builder extends AbstractBuilder<Builder>{
        private List<Pagina> paginas;
        private String taal;
        private String code;
        private String type;
        private Boolean actief = Boolean.FALSE;

        private Builder() {
        }

        public Builder withPaginas(List<Pagina> paginas) {
            this.paginas = paginas;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withActief(Boolean actief) {
            this.actief = actief;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }


        public Brief build() {
            return new Brief(this);
        }
    }
}

package be.artisjaap.document.action.to;

import java.time.LocalDateTime;
import java.util.List;

public class BriefTO {

    private String code;
    private String type;
    private List<PaginaTO> paginas;
    private String taal;
    private String referentie;
    private String id;
    private Boolean actief;
    private LocalDateTime aangemaaktOp;

    public String getNaam() {
        return code;
    }

    public List<PaginaTO> getPaginas() {
        return paginas;
    }

    public String getTaal() {
        return taal;
    }

    public String getId() {
        return id;
    }

    public String getReferentie() {
        return referentie;
    }

    public String getType() {
        return type;
    }

    public Boolean getActief() {
        return actief;
    }

    public LocalDateTime getAangemaaktOp() {
        return aangemaaktOp;
    }

    private BriefTO(Builder builder) {
        code = builder.code;
        paginas = builder.paginas;
        taal = builder.taal;
        id = builder.id;
        referentie = builder.referentie;
        type = builder.type;
        actief = builder.actief;
        aangemaaktOp = builder.aangemaaktOp;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String code;
        private List<PaginaTO> paginas;
        private String taal;
        private String id;
        private String referentie;
        private String type;
        private Boolean actief;
        private LocalDateTime aangemaaktOp;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withPaginas(List<PaginaTO> paginas) {
            this.paginas = paginas;
            return this;
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withReferentie(String referentie) {
            this.referentie = referentie;
            return this;
        }

        public Builder withActief(Boolean actief) {
            this.actief = actief;
            return this;
        }

        public Builder withAangemaaktOp(LocalDateTime aangemaaktOp) {
            this.aangemaaktOp = aangemaaktOp;
            return this;
        }


        public BriefTO build() {
            return new BriefTO(this);
        }
    }
}

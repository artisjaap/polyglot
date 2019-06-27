package be.artisjaap.document.model;

import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document(collection = "BwBrief")
public class Document extends AbstractDocument{

    private List<Page> pages;
    private String taal;
    private String code;
    private Boolean actief;
    private String type;

    public String getCode() {
        return code;
    }

    private Document(){}

    private Document(Builder builder) {
        buildCommon(builder);
        pages = builder.pages;
        taal = builder.taal;
        code = builder.code;
        actief = builder.actief;
        type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Page> getPages() {
        return pages;
    }

    public String getTaal() {
        return taal;
    }

    public void deactivate() {
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
        private List<Page> pages;
        private String taal;
        private String code;
        private String type;
        private Boolean actief = Boolean.FALSE;

        private Builder() {
        }

        public Builder withPaginas(List<Page> pages) {
            this.pages = pages;
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


        public Document build() {
            return new Document(this);
        }
    }
}

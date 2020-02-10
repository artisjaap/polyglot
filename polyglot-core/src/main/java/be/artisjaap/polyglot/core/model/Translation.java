package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PolTranslation")
public class Translation extends AbstractDocument {

    private ObjectId languagePairId;

    private String languageA;
    private String languageB;

    public String getLanguageA() {
        return languageA;
    }

    public String getLanguageB() {
        return languageB;
    }

    public ObjectId getLanguagePairId() {
        return languagePairId;
    }

    public void setLanguageA(String languageA) {
        this.languageA = languageA;
    }

    public void setLanguageB(String languageB) {
        this.languageB = languageB;
    }

    private Translation(){}

    private Translation(Builder builder) {
        languagePairId = builder.languagePairId;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder>{
        private ObjectId languagePairId;
        private String languageA;
        private String languageB;

        private Builder() {
        }

        public Builder withLanguagePairId(ObjectId languagePairId) {
            this.languagePairId = languagePairId;
            return this;
        }

        public Builder withLanguageA(String languageA) {
            this.languageA = languageA;
            return this;
        }

        public Builder withLanguageB(String languageB) {
            this.languageB = languageB;
            return this;
        }

        public Translation build() {
            return new Translation(this);
        }
    }
}

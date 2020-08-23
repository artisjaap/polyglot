package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "PolTranslation")
public class Translation extends AbstractDocument {

    private ObjectId languagePairId;

    private Set<String> languageA = new HashSet<>();

    private Set<String> languageB = new HashSet<>();

    public Set<String> getLanguageA() {
        return languageA;
    }

    public Set<String> getLanguageB() {
        return languageB;
    }

    public ObjectId getLanguagePairId() {
        return languagePairId;
    }

    public void setLanguageA(Set<String> languageA) {
        this.languageA = languageA;
    }

    public void setLanguageB(Set<String> languageB) {
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
        private Set<String> languageA = new HashSet<>();
        private Set<String> languageB = new HashSet<>();

        private Builder() {
        }

        public Builder withLanguagePairId(ObjectId languagePairId) {
            this.languagePairId = languagePairId;
            return this;
        }

        public Builder withLanguageA(String languageA) {
            this.languageA.add(languageA);
            return this;
        }

        public Builder withLanguageB(String languageB) {
            this.languageB.add(languageB);
            return this;
        }

        public Builder withLanguageA(Set<String> languageA) {
            this.languageA = languageA;
            return this;
        }

        public Builder withLanguageB(Set<String> languageB) {
            this.languageB = languageB;
            return this;
        }

        public Translation build() {
            return new Translation(this);
        }
    }
}

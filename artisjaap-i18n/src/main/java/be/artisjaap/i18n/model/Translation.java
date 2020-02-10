package be.artisjaap.i18n.model;

import be.artisjaap.common.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Translation")
public class Translation extends AbstractDocument {
    private String key;
    private String translation;
    private String languageIsoCode;
    private String bundleName;

    protected Translation(){

    }

    private Translation(Builder builder) {
        buildCommon(builder);
        key = builder.key;
        translation = builder.translation;
        languageIsoCode = builder.languageIsoCode;
        bundleName = builder.bundleName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return translation;
    }

    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public static final class Builder extends AbstractBuilder<Builder>{
        private String key;
        private String translation;
        private String languageIsoCode;
        private String bundleName;

        private Builder() {
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withTranslation(String translation) {
            this.translation = translation;
            return this;
        }

        public Builder withLanguageIsoCode(String languageIsoCode) {
            this.languageIsoCode = languageIsoCode;
            return this;
        }

        public Builder withBundleName(String bundleName) {
            this.bundleName = bundleName;
            return this;
        }

        public Translation build() {
            return new Translation(this);
        }
    }
}

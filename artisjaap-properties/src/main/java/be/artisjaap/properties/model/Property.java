package be.artisjaap.properties.model;

import be.artisjaap.common.model.AbstractDocument;

public class Property extends AbstractDocument {

    private String key;
    private String value;
    private PropertyType type;

    private Property(Builder builder) {
        key = builder.key;
        value = builder.value;
        type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public PropertyType getType() {
        return type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public static final class Builder {
        private String key;
        private String value;
        private PropertyType type;

        private Builder() {
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withType(PropertyType type) {
            this.type = type;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}

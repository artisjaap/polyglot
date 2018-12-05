package be.artisjaap.properties.model;

import be.artisjaap.common.model.AbstractDocument;

public class Property extends AbstractDocument {

    private String key;
    private Object value;

    private Property(){}

    private Property(Builder builder) {
        setKey(builder.key);
        setValue(builder.value);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public static final class Builder {
        private String key;
        private Object value;

        private Builder() {
        }

        public Builder withKey(String val) {
            key = val;
            return this;
        }

        public Builder withValue(Object val) {
            value = val;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}

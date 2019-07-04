package be.artisjaap.properties.action.to;

import be.artisjaap.properties.model.PropertyType;

public class PropertyTO {
    private String key;
    private String value;
    private PropertyType actualType;

    private PropertyTO(Builder builder) {
        key = builder.key;
        value = builder.value;
        actualType = builder.actualType;
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

    public PropertyType getActualType() {
        return actualType;
    }

    public static final class Builder {
        private String key;
        private String value;
        private PropertyType actualType;

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

        public Builder withActualType(PropertyType actualType) {
            this.actualType = actualType;
            return this;
        }

        public PropertyTO build() {
            return new PropertyTO(this);
        }
    }
}

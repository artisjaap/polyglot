package be.artisjaap.properties.web.endpoints.response;

import be.artisjaap.properties.action.to.PropertyTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyResponse {
    private String key;
    private String value;
    private String actualType;

    private PropertyResponse(Builder builder) {
        key = builder.key;
        value = builder.value;
        actualType = builder.actualType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static PropertyResponse from(PropertyTO propertyTO){
        return PropertyResponse.newBuilder()
                .withKey(propertyTO.getKey())
                .withValue(propertyTO.getValue())
                .withActualType(propertyTO.getActualType())

                .build();
    }

    public static List<PropertyResponse> from(Collection<PropertyTO> propertyTO){
        return propertyTO.stream().map(PropertyResponse::from).collect(Collectors.toList());
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getActualType() {
        return actualType;
    }

    public static final class Builder {
        private String key;
        private String value;
        private String actualType;

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

        public Builder withActualType(String actualType) {
            this.actualType = actualType;
            return this;
        }

        public PropertyResponse build() {
            return new PropertyResponse(this);
        }
    }
}

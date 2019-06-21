package be.artisjaap.angular.generator.action.vo;

import java.util.List;

public class ClassVO {
    private String name;
    private List<ClassPropertyVO> properties;

    private ClassVO(Builder builder) {
        setName(builder.name);
        setProperties(builder.properties);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassPropertyVO> getProperties() {
        return properties;
    }

    public void setProperties(List<ClassPropertyVO> properties) {
        this.properties = properties;
    }

    public static final class Builder {
        private String name;
        private List<ClassPropertyVO> properties;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withProperties(List<ClassPropertyVO> properties) {
            this.properties = properties;
            return this;
        }

        public ClassVO build() {
            return new ClassVO(this);
        }
    }
}

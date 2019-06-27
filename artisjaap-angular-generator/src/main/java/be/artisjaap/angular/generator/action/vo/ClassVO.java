package be.artisjaap.angular.generator.action.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassVO {
    private String name;
    private Class<?> forClass;
    private List<ClassPropertyVO> properties;

    private ClassVO(Builder builder) {
        setName(builder.name);
        setProperties(builder.properties);
        forClass = builder.forClass;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Class<?> getForClass() {
        return forClass;
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
        private List<ClassPropertyVO> properties = new ArrayList<>();
        private Class forClass;

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

        public Builder withForClass(Class forClass){
            this.forClass = forClass;
            return this;
        }

        public ClassVO build() {
            return new ClassVO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassVO classVO = (ClassVO) o;
        return forClass.equals(classVO.forClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forClass);
    }
}

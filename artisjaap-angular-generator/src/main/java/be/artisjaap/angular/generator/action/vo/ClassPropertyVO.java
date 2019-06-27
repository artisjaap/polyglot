package be.artisjaap.angular.generator.action.vo;

public class ClassPropertyVO {
    private String name;
    private String type;
    private ClassVO typeClass;
    private boolean isList;

    private ClassPropertyVO(Builder builder) {
        setName(builder.name);
        setType(builder.type);
        this.isList = builder.isList;
        this.typeClass = builder.typeClass;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isList() {
        return isList;
    }

    public ClassVO getTypeClass() {
        return typeClass;
    }

    public static final class Builder {
        private String name;
        private String type;
        private boolean isList = false;
        private ClassVO typeClass;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ClassPropertyVO build() {
            return new ClassPropertyVO(this);
        }

        public Builder withIsListType(boolean list) {
            this.isList = list;
            return this;
        }

        public Builder withTypeClass(ClassVO typeClass){
            this.typeClass = typeClass;
            return this;
        }
    }
}

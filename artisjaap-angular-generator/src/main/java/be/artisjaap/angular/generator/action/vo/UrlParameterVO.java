package be.artisjaap.angular.generator.action.vo;

public class UrlParameterVO {
    private String name;
    private String type;

    private UrlParameterVO(Builder builder) {
        setName(builder.name);
        setType(builder.type);
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


    public static final class Builder {
        private String name;
        private String type;

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

        public UrlParameterVO build() {
            return new UrlParameterVO(this);
        }
    }
}

package be.artisjaap.angular.generator.action.vo;

import java.util.List;

public class ServiceClassVO {
    private String name;
    private List<String> ngServiceMethods;

    private ServiceClassVO(Builder builder) {
        name = builder.name;
        ngServiceMethods = builder.ngServiceMethods;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getName() {
        return name;
    }

    public List<String> getNgServiceMethods() {
        return ngServiceMethods;
    }

    public static final class Builder {
        private String name;
        private List<String> ngServiceMethods;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNgServiceMethods(List<String> ngServiceMethods) {
            this.ngServiceMethods = ngServiceMethods;
            return this;
        }

        public ServiceClassVO build() {
            return new ServiceClassVO(this);
        }

        public List<String> getNgServiceMethods() {
            return ngServiceMethods;
        }

        public String getName() {
            return name;
        }
    }
}

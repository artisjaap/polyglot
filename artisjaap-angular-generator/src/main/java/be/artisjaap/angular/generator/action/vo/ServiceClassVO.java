package be.artisjaap.angular.generator.action.vo;

import java.util.List;
import java.util.Set;

public class ServiceClassVO {
    private String name;
    private List<ServiceMethodVO> ngServiceMethods;
    private Set<ClassVO> imports;

    private ServiceClassVO(Builder builder) {
        name = builder.name;
        ngServiceMethods = builder.ngServiceMethods;
        imports = builder.imports;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getName() {
        return name;
    }

    public Set<ClassVO> getImports() {
        return imports;
    }

    public List<ServiceMethodVO> getNgServiceMethods() {
        return ngServiceMethods;
    }

    public static final class Builder {
        private String name;
        private List<ServiceMethodVO> ngServiceMethods;
        private Set<ClassVO> imports;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNgServiceMethods(List<ServiceMethodVO> ngServiceMethods) {
            this.ngServiceMethods = ngServiceMethods;
            return this;
        }

        public Builder withImports(Set<ClassVO> classes){
            this.imports = classes;
            return this;
        }

        public ServiceClassVO build() {
            return new ServiceClassVO(this);
        }

        public List<ServiceMethodVO> getNgServiceMethods() {
            return ngServiceMethods;
        }

        public String getName() {
            return name;
        }
    }
}

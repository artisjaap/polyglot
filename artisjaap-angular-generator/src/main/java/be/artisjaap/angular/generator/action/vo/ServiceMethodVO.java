package be.artisjaap.angular.generator.action.vo;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

public class ServiceMethodVO {

    private String methodName;
    private String returnType;
    private Boolean returnsList;
    private String endpoint;
    private RequestMethod requestMethod;
    private List<UrlParameterVO> urlParameters;
    private String bodyType;
    private Set<ClassVO> requiredClasses;

    private ServiceMethodVO(Builder builder) {
        methodName = builder.methodName;
        returnType = builder.returnType;
        returnsList = builder.returnsList;
        endpoint = builder.endpoint;
        requestMethod = builder.requestMethod;
        urlParameters = builder.urlParameters;
        bodyType = builder.bodyType;
        requiredClasses = builder.requiredClasses;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public Boolean getReturnsList() {
        return returnsList;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public List<UrlParameterVO> getUrlParameters() {
        return urlParameters;
    }

    public String getBodyType() {
        return bodyType;
    }

    public Set<ClassVO> getRequiredClasses() {
        return requiredClasses;
    }

    public static final class Builder {
        private String methodName;
        private String returnType;
        private Boolean returnsList;
        private String endpoint;
        private RequestMethod requestMethod;
        private List<UrlParameterVO> urlParameters = new ArrayList<>();
        private String bodyType;
        private Set<ClassVO> requiredClasses = new HashSet<>();

        private Builder() {
        }

        public Builder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder withReturnType(String returnType) {
            this.returnType = returnType;
            return this;
        }

        public Builder withReturnsList(Boolean returnsList) {
            this.returnsList = returnsList;
            return this;
        }

        public Builder withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder withRequestMethod(RequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder withUrlParameters(List<UrlParameterVO> urlParameters) {
            this.urlParameters = urlParameters;
            return this;
        }

        public Builder withBodyType(String bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        public ServiceMethodVO build() {
            return new ServiceMethodVO(this);
        }

        public Builder withRequiredClasses(Collection<ClassVO> requiredClasses) {
            this.requiredClasses.addAll(requiredClasses);
            return this;
        }
    }
}


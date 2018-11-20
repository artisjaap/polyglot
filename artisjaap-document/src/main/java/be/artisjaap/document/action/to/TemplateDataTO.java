package be.artisjaap.document.action.to;

import org.bson.types.ObjectId;

import java.util.*;

public class TemplateDataTO {

    private String code;
    private Optional<byte[]> data;
    private Set<ObjectId> gebruikteTemplates;

    public static TemplateDataTO empty() {
        return newBuilder().build();
    }

    public Optional<byte[]> getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public Set<ObjectId> getGebruikteTemplates() {
        return gebruikteTemplates;
    }

    private TemplateDataTO(Builder builder) {
        data = builder.data;
        gebruikteTemplates = builder.gebruikteTemplates;
        code = builder.code;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Optional<byte[]> data = Optional.empty();
        private Set<ObjectId> gebruikteTemplates = new HashSet<>();
        private String code;

        private Builder() {
        }

        public Builder withData(Optional<byte[]> data) {
            this.data = data;
            return this;
        }

        public Builder withGebruikteTemplates(Set<ObjectId> gebruikteTemplates) {
            this.gebruikteTemplates = gebruikteTemplates;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public TemplateDataTO build() {
            return new TemplateDataTO(this);
        }
    }
}

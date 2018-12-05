package be.artisjaap.mail.action.to;

import be.artisjaap.mail.model.Attachment;

import java.util.List;

public class MailAndTemplateTO {
    private String templateCode;
    private String from;
    private String to;
    private String username;
    private String password;
    private List<Attachment> attachments;
    private Object model;

    private MailAndTemplateTO(Builder builder) {
        templateCode = builder.templateCode;
        from = builder.from;
        to = builder.to;
        username = builder.username;
        password = builder.password;
        attachments = builder.attachments;
        model = builder.model;
    }

    public static Builder newBuilder() {
        return new Builder();
    }



    public String templateCode() {
        return templateCode;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public List<Attachment> attachments() {
        return attachments;
    }

    public Object model() {
        return model;
    }

    public static final class Builder {
        private String templateCode;
        private String from;
        private String to;
        private String username;
        private String password;
        private List<Attachment> attachments;
        private Object model;

        private Builder() {
        }

        public Builder withTemplateCode(String val) {
            templateCode = val;
            return this;
        }

        public Builder withFrom(String val) {
            from = val;
            return this;
        }

        public Builder withTo(String val) {
            to = val;
            return this;
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withAttachments(List<Attachment> val) {
            attachments = val;
            return this;
        }

        public Builder withModel(Object val) {
            model = val;
            return this;
        }

        public MailAndTemplateTO build() {
            return new MailAndTemplateTO(this);
        }
    }
}

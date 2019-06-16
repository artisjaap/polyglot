package be.artisjaap.mail.action.to;

import be.artisjaap.mail.model.Attachment;

import java.util.List;

public class MailTO {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String username;
    private String password;
    private List<Attachment> attachments;

    public static Builder newBuilder(MailTO copy) {
        Builder builder = new Builder();
        builder.to = copy.to();
        builder.subject = copy.subject();
        builder.body = copy.body();
        builder.username = copy.username();
        builder.password = copy.password();
        builder.attachments = copy.attachments();
        return builder;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public String body() {
        return body;
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

    private MailTO(Builder builder) {
        to = builder.to;
        subject = builder.subject;
        body = builder.body;
        username = builder.username;
        password = builder.password;
        attachments = builder.attachments;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String from;
        private String to;
        private String subject;
        private String body;
        private String username;
        private String password;
        private List<Attachment> attachments;

        private Builder() {
        }

        public Builder withFrom(String val) {
            from = val;
            return this;
        }

        public Builder withTo(String val) {
            to = val;
            return this;
        }

        public Builder withSubject(String val) {
            subject = val;
            return this;
        }

        public Builder withBody(String val) {
            body = val;
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

        public MailTO build() {
            return new MailTO(this);
        }
    }
}

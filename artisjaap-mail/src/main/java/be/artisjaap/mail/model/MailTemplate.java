package be.artisjaap.mail.model;

import be.artisjaap.common.model.AbstractDocument;

public class MailTemplate extends AbstractDocument {

    private String code;
    private String htmlBody;
    private String subject;

    private MailTemplate(Builder builder) {
        code = builder.code;
        htmlBody = builder.htmlBody;
        subject = builder.subject;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public String getSubject() {
        return subject;
    }


    public static final class Builder {
        private String code;
        private String htmlBody;
        private String subject;

        private Builder() {
        }

        public Builder withCode(String val) {
            code = val;
            return this;
        }

        public Builder withHtmlBody(String val) {
            htmlBody = val;
            return this;
        }

        public Builder withSubject(String val) {
            subject = val;
            return this;
        }

        public MailTemplate build() {
            return new MailTemplate(this);
        }
    }
}

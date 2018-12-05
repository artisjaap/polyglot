package be.artisjaap.mail.action.to;

public class MailTemplateTO {

    private String code;
    private String htmlBody;
    private String subject;

    private MailTemplateTO(Builder builder) {
        code = builder.code;
        htmlBody = builder.htmlBody;
        subject = builder.subject;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String code() {
        return code;
    }

    public String htmlBody() {
        return htmlBody;
    }

    public String subject() {
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

        public MailTemplateTO build() {
            return new MailTemplateTO(this);
        }
    }
}

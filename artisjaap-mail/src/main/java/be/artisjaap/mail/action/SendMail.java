package be.artisjaap.mail.action;

import be.artisjaap.mail.action.to.MailAndTemplateTO;
import be.artisjaap.mail.action.to.MailTO;
import be.artisjaap.mail.action.to.MailTemplateTO;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

    private final GMail gmail;

    private final FindMailTemplate mailTemplate;

    private final FreemarkerTemplateGenerator freemarkerTemplateGenerator;

    public SendMail(GMail gmail, FindMailTemplate mailTemplate, FreemarkerTemplateGenerator freemarkerTemplateGenerator) {
        this.gmail = gmail;
        this.mailTemplate = mailTemplate;
        this.freemarkerTemplateGenerator = freemarkerTemplateGenerator;
    }

    public void to(MailTO mailTO) {
        gmail.sendAMail(mailTO);
    }

    public void fromTemplate(MailAndTemplateTO mailAndTemplateTO) {
        MailTemplateTO mailTemplateTO = mailTemplate.forCode(mailAndTemplateTO.getTemplateCode());
        String mailBody = freemarkerTemplateGenerator.fillTemplate(mailTemplateTO.getHtmlBody(), mailAndTemplateTO.getModel());
        String subject = freemarkerTemplateGenerator.fillTemplate(mailTemplateTO.getSubject(), mailAndTemplateTO.getModel());

        to(MailTO.builder()
                .attachments(mailAndTemplateTO.getAttachments())
                .body(mailBody)
                .from(mailAndTemplateTO.getFrom())
                .to(mailAndTemplateTO.getTo())
                .subject(subject)
                .username(mailAndTemplateTO.getUsername())
                .password(mailAndTemplateTO.getPassword())
                .build());
    }
}

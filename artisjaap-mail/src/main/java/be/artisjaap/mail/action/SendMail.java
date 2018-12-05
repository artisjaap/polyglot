package be.artisjaap.mail.action;

import be.artisjaap.mail.action.to.MailAndTemplateTO;
import be.artisjaap.mail.action.to.MailTO;
import be.artisjaap.mail.action.to.MailTemplateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

    @Autowired
    private GMail gmail;

    @Autowired
    private FindMailTemplate mailTemplate;

    @Autowired
    private FreemarkerTemplateGenerator freemarkerTemplateGenerator;

    public void to(MailTO mailTO){
        gmail.sendAMail(mailTO);
    }

    public void fromTemplate(MailAndTemplateTO mailAndTemplateTO ){
        MailTemplateTO mailTemplateTO = mailTemplate.forCode(mailAndTemplateTO.templateCode());
        String mailBody = freemarkerTemplateGenerator.fillTemplate(mailTemplateTO.htmlBody(), mailAndTemplateTO.model());
        String subject = freemarkerTemplateGenerator.fillTemplate(mailTemplateTO.subject(), mailAndTemplateTO.model());

        to(MailTO.newBuilder()
                .withAttachments(mailAndTemplateTO.attachments())
                .withBody(mailBody)
                .withFrom(mailAndTemplateTO.from())
                .withTo(mailAndTemplateTO.to())
                .withSubject(subject)
                .withUsername(mailAndTemplateTO.username())
                .withPassword(mailAndTemplateTO.password())
                .build());
    }
}

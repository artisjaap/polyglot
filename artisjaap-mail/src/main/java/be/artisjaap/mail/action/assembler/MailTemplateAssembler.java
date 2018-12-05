package be.artisjaap.mail.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.mail.action.to.MailTemplateTO;
import be.artisjaap.mail.model.MailTemplate;
import org.springframework.stereotype.Component;

@Component
public class MailTemplateAssembler implements Assembler<MailTemplateTO, MailTemplate> {
    @Override
    public MailTemplateTO assembleTO(MailTemplate doc) {
        return MailTemplateTO.newBuilder()
                .withCode(doc.getCode())
                .withHtmlBody(doc.getHtmlBody())
                .withSubject(doc.getSubject())
                .build();

    }

    @Override
    public MailTemplate assembleEntity(MailTemplateTO mailTemplateTO) {
        return MailTemplate.newBuilder()
                .withCode(mailTemplateTO.code())
                .withHtmlBody(mailTemplateTO.htmlBody())
                .withSubject(mailTemplateTO.subject())
                .build();
    }
}

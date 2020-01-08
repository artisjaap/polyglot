package be.artisjaap.mail.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.mail.action.to.MailTemplateTO;
import be.artisjaap.mail.model.MailTemplate;
import org.springframework.stereotype.Component;

@Component
public class MailTemplateAssembler implements Assembler<MailTemplateTO, MailTemplate> {
    @Override
    public MailTemplateTO assembleTO(MailTemplate doc) {
        return MailTemplateTO.builder()
                .code(doc.getCode())
                .htmlBody(doc.getHtmlBody())
                .subject(doc.getSubject())
                .build();

    }

    @Override
    public MailTemplate assembleEntity(MailTemplateTO mailTemplateTO) {
        return MailTemplate.newBuilder()
                .withCode(mailTemplateTO.getCode())
                .withHtmlBody(mailTemplateTO.getHtmlBody())
                .withSubject(mailTemplateTO.getSubject())
                .build();
    }
}

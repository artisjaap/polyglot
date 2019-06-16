package be.artisjaap.mail.action;

import be.artisjaap.mail.action.assembler.MailTemplateAssembler;
import be.artisjaap.mail.action.to.MailTemplateTO;
import be.artisjaap.mail.model.mongo.MailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveMailTemplate {
    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    @Autowired
    private MailTemplateAssembler mailTemplateAssembler;

    public MailTemplateTO forTemplate(MailTemplateTO template){
        mailTemplateRepository.saveOrUpdate(mailTemplateAssembler.assembleEntity(template));
        return template;
    }
}

package be.artisjaap.mail.model.mongo;

import be.artisjaap.mail.model.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MailTemplateRepositoryImpl implements MailTemplateRepositoryCustom {
    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    @Override
    public void saveOrUpdate(MailTemplate template) {
        MailTemplate mailTemplate = mailTemplateRepository.findByCode(template.getCode())
                .map(t -> {
                    t.setSubject(template.getSubject());
                    t.setHtmlBody(template.getHtmlBody());
                    t.setCode(template.getCode());
                    return t;
                })
                .orElse(template);

        mailTemplateRepository.save(mailTemplate);

    }
}

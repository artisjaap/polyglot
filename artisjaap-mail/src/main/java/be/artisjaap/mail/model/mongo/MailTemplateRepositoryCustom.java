package be.artisjaap.mail.model.mongo;

import be.artisjaap.mail.model.MailTemplate;

public interface MailTemplateRepositoryCustom {
    void saveOrUpdate(MailTemplate assembleEntity);
}

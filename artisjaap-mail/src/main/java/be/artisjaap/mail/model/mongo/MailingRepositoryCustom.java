package be.artisjaap.mail.model.mongo;

import be.artisjaap.mail.model.Mailing;
import org.springframework.data.domain.Page;

public interface MailingRepositoryCustom {

	Page<Mailing> findGebruikersFor(String to, String subject, Integer page);
}

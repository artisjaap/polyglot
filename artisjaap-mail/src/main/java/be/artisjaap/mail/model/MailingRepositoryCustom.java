package be.artisjaap.mail.model;

import org.springframework.data.domain.Page;

public interface MailingRepositoryCustom {

	Page<Mailing> findGebruikersFor(String to, String subject, Integer page);
}

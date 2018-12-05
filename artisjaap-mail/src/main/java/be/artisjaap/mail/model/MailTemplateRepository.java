package be.artisjaap.mail.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MailTemplateRepository extends MongoRepository<MailTemplate, ObjectId>, MailTemplateRepositoryCustom {

    Optional<MailTemplate> findByCode(String code);
}

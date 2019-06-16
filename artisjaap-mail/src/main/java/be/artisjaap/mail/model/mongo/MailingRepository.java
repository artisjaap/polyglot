package be.artisjaap.mail.model.mongo;

import be.artisjaap.mail.model.Mailing;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailingRepository extends MongoRepository<Mailing, ObjectId> , MailingRepositoryCustom{

}

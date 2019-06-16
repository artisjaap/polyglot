package be.artisjaap.i18n.model.mongo;

import be.artisjaap.i18n.model.Translation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslationRepository extends MongoRepository<Translation, ObjectId> {
}

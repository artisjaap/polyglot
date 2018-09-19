package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TranslationRepository extends MongoRepository<Translation, ObjectId> {
//    List<Translation> findByIdIn(List<ObjectId> ids)
}

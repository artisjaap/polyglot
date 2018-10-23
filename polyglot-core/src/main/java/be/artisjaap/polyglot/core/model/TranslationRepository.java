package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslationRepository extends MongoRepository<Translation, ObjectId>, TranslationRepositoryCustom {
//    List<Translation> findByIdIn(List<ObjectId> ids)



}

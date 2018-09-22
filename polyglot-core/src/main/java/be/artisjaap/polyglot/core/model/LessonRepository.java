package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends MongoRepository<Lesson, ObjectId> {

    List<Lesson> findByUserIdAndLanguagePairId(ObjectId userId, ObjectId languageId);

    Optional<Lesson> findByUserIdAndName(ObjectId userId, String name);
}

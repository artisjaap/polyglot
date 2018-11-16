package be.artisjaap.polyglot.core.model;

import be.artisjaap.core.validation.ValidationException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends MongoRepository<Lesson, ObjectId> {

    List<Lesson> findByLanguagePairId(ObjectId languagePairId);

    List<Lesson> findByUserIdAndLanguagePairId(ObjectId userId, ObjectId languageId);

    Optional<Lesson> findByUserIdAndName(ObjectId userId, String name);

    List<Lesson> findByUserId(String userId);

    default Lesson findByIdOrThrow(ObjectId id){
        return findById(id).orElseThrow(() -> new ValidationException("DOC_NOT_FOUND"));
    }
}

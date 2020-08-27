package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LessonPracticeRepository extends MongoRepository<LessonPractice, ObjectId>{

    Optional<LessonPractice> findByLessonId(ObjectId lessonId);
}


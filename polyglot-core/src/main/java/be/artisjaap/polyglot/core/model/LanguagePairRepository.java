package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LanguagePairRepository extends MongoRepository<LanguagePair, ObjectId> {

    List<LanguagePair> findAllByUserId(ObjectId userId);

    Optional<LanguagePair> findByUserIdAndLanguageFromAndLanguageTo(ObjectId userId, String languageFrom, String languageTo);
}

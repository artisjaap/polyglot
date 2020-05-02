package be.artisjaap.polyglot.core.model.verbs;

import be.artisjaap.polyglot.core.model.LanguagePair;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerbRepository extends MongoRepository<Verb, ObjectId> {

    Optional<Verb> findByInfinitiveLanguageAAndInfinitiveLanguageBAndLanguagePairId(String infinitiveLanguageA, String infinitiveLanguageB, ObjectId languagePairId);

}

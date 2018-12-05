package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.validation.ValidationException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TranslationRepository extends MongoRepository<Translation, ObjectId>, TranslationRepositoryCustom {
//    List<Translation> findByIdIn(List<ObjectId> ids)

    List<Translation> findByLanguagePairIdAndLanguageAIn(ObjectId languagePairId, List<String> languageA);

    default Translation findByIdOrThrow(ObjectId toObjectId){
        return findById(toObjectId).orElseThrow(() -> new ValidationException("TRANSLATION_NOT_FOUND"));
    }
}

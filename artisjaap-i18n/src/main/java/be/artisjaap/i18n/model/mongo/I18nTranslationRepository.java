package be.artisjaap.i18n.model.mongo;

import be.artisjaap.i18n.model.Translation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface I18nTranslationRepository extends MongoRepository<Translation, ObjectId> {
    List<Translation> findAllByBundleName(String bundleName);
    List<Translation> findAllByLanguageIsoCodeAndBundleName(String language, String bundleName);

    Optional<Translation> findByKeyAndLanguageIsoCode(String key, String language);
}

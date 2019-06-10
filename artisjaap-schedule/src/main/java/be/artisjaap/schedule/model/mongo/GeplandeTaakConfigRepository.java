package be.artisjaap.schedule.model.mongo;

import be.artisjaap.schedule.model.GeplandeTaakConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GeplandeTaakConfigRepository extends MongoRepository<GeplandeTaakConfig, ObjectId> {

	Optional<GeplandeTaakConfig> findByCode(String code);

}

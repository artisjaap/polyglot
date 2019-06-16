package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.GecombineerdeTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GecombineerdeTemplateRepository extends MongoRepository<GecombineerdeTemplate, ObjectId> {
    @Query("{code : ?0, taal : ?1, actief : true}")
    Optional<GecombineerdeTemplate> findByCodeAndTaalAndActief(String code, String taal);

    List<GecombineerdeTemplate> findByCode(String code);

    List<GecombineerdeTemplate> findByCodeAndTaal(String code, String taal);
}

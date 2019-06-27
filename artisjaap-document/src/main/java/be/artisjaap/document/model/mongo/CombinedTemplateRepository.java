package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.CombinedTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CombinedTemplateRepository extends MongoRepository<CombinedTemplate, ObjectId> {
    @Query("{code : ?0, taal : ?1, actief : true}")
    Optional<CombinedTemplate> findByCodeAndTaalAndActief(String code, String taal);

    List<CombinedTemplate> findByCode(String code);

    List<CombinedTemplate> findByCodeAndTaal(String code, String taal);
}

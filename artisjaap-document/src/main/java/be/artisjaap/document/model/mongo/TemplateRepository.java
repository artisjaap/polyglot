package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.Template;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends MongoRepository<Template, ObjectId> {

    @Query("{code : ?0, taal : ?1, actief : true}")
    Optional<Template> findByCodeAndTaalAndActief(String code, String taal);

    List<Template> findByCode(String code);

    List<Template> findByCodeAndTaal(String code, String taal);
}


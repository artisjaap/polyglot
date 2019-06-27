package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<Document, ObjectId>, BriefRepositoryCustom {

    @Query("{code : ?0, taal : ?1, actief : true}")
    Optional<Document> findByCodeAndTaalAndActief(String code, String taal);

    List<Document> findByCodeAndTaal(String code, String taal);

    List<Document> findByCode(String code);
}

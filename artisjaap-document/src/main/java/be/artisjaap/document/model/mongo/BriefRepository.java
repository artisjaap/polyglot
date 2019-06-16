package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.Brief;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BriefRepository extends MongoRepository<Brief, ObjectId>, BriefRepositoryCustom {

    @Query("{code : ?0, taal : ?1, actief : true}")
    Optional<Brief> findByCodeAndTaalAndActief(String code, String taal);

    List<Brief> findByCodeAndTaal(String code, String taal);

    List<Brief> findByCode(String code);
}

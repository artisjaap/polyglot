package be.artisjaap.schedule.model.mongo;

import be.artisjaap.schedule.model.TaakInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaakInfoRepository extends MongoRepository<TaakInfo, ObjectId> {

    Optional<TaakInfo> findByTaakCode(String code);

}

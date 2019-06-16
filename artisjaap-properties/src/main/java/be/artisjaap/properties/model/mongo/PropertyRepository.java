package be.artisjaap.properties.model.mongo;

import be.artisjaap.properties.model.Property;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PropertyRepository extends MongoRepository<Property, ObjectId>, PropertyRepositoryCustom {

    Optional<Property> findByKey(String key);
}

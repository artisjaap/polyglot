package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.GegenereerdeBrief;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GegenereerdeBriefRepository extends MongoRepository<GegenereerdeBrief, ObjectId> {


    List<GegenereerdeBrief> findByBriefCodeAndTaal(String code, String taal);
}

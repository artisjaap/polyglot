package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.BriefCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BriefCodeRepository extends MongoRepository<BriefCode, ObjectId> {
}

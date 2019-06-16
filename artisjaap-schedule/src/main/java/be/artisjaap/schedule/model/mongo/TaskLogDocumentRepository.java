package be.artisjaap.schedule.model.mongo;

import be.artisjaap.schedule.model.TaskLogDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskLogDocumentRepository extends MongoRepository<TaskLogDocument, ObjectId> {

}

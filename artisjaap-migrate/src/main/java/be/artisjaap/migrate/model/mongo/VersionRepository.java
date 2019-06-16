package be.artisjaap.migrate.model.mongo;

import be.artisjaap.migrate.model.Version;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VersionRepository  extends MongoRepository<Version, ObjectId>, VersionRepositoryCustom {
    Version findByVersion(String version);
}

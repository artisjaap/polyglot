package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.TemplateCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TemplateCodeRepository extends MongoRepository<TemplateCode, ObjectId> {
    List<TemplateCode> findByCodeIn(List<String> templateCodes);
}

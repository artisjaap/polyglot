package be.artisjaap.document.model.mongo;

import be.artisjaap.document.model.GecombineerdeTemplateCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CombinedTemplateCodeRepository extends MongoRepository<GecombineerdeTemplateCode, ObjectId> {
    List<GecombineerdeTemplateCode> findByCodeIn(List<String> templateCodes);
}

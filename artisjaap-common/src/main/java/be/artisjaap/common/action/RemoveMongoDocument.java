package be.artisjaap.common.action;

import be.artisjaap.common.model.AbstractDocument;
import be.artisjaap.common.model.mongo.GenericMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveMongoDocument {

    private final GenericMongoRepository genericMongoRepository;

    private RemoveMongoDocument(GenericMongoRepository genericMongoRepository){
        this.genericMongoRepository = genericMongoRepository;
    }

    public <T extends AbstractDocument> void forDocument(Class<T> clazz, ObjectId id) {
        genericMongoRepository.removeFrom(clazz, id);
    }

}

package be.artisjaap.common.model.mongo;

import be.artisjaap.common.model.AbstractDocument;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GenericMongoRepository {

    private final static String REMOVED_COLLECTION_POSTFIX = "_deleted";

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T extends AbstractDocument> void removeFrom(Class<T> clazz, ObjectId id){
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        T doc = mongoTemplate.findOne(q, clazz);
        saveDocumentAsDeleted(doc);
        mongoTemplate.remove(q, clazz);
    }



    public <T extends AbstractDocument> void resurrect(Class<T> clazz, ObjectId id){
        String removedCollection = removedCollectionName(clazz);
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        T doc = mongoTemplate.findOne(q, clazz, removedCollection);
        if(doc != null) {
            mongoTemplate.save(doc);
            mongoTemplate.remove(doc, removedCollection);
        }
    }

    private <T extends AbstractDocument> void saveDocumentAsDeleted(T doc) {
        mongoTemplate.save(doc, removedCollectionName(doc.getClass()));

    }

    private <T extends AbstractDocument> String removedCollectionName(Class<T> clazz){
        return mongoTemplate.getCollectionName(clazz) + REMOVED_COLLECTION_POSTFIX;
    }
}

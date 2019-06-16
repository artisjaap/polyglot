package be.artisjaap.backup.action;

import be.artisjaap.backup.action.to.CollectionDataTO;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import be.artisjaap.backup.utils.ZipUtils;

@Component
public class RestoreData {
    private final static Logger logger = LogManager.getLogger();

    @Autowired
    private MongoTemplate mongoTemplate;

    public void restore(CollectionDataTO collectionDataTO) throws IOException {
        InputStream in = new BufferedInputStream(collectionDataTO.getData());
        ZipInputStream zis = new ZipInputStream(in);

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String filename = entry.getName();
            String collection = filename.substring(0, filename.length() - 5);
            //schoolcupWS
            //		.mastercast(new Message(MessageTopic.STATUS_UPDATE, "Collection " + collection + " Restoring..."));
            ByteArrayOutputStream output = ZipUtils.readFileFromZipStream(zis);
            saveStreamToMongo(output, collection, collectionDataTO.getRestoreMode());
//            logger.info("restoring collection: " + collection);
        }
    }

    private void saveStreamToMongo(ByteArrayOutputStream output, String collection, CollectionDataTO.RestoreMode restoreMode)
            throws IOException {
        BufferedReader bin = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toByteArray())));
        String line = null;

        while ((line = bin.readLine()) != null) {

            try {
                DBObject object = (DBObject) JSON.parse(line);

                switch (restoreMode) {
                    case DELETE_INSERT:
                        deleteInsert(collection, object);
                        break;
                    case INSERT_NEW:
                        insertIfNew(collection, object);
                        break;
                    case UPSERT:
                        mongoTemplate.save(object, collection);
                        break;
                }

                if (collection.equals("gebruiker")) {
                    logger.info("saving..." + object);
                }

            } catch (JSONParseException jpx) {
                System.out.println("cannot serialize: " + line);
            } catch (Exception e) {
                System.out.println("cannot serialize: " + line);
            }
        }
    }
    private void insertIfNew(String collection, DBObject object) {
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(object.get("_id")));
        Object doc = mongoTemplate.findById(object.get("_id"), Object.class, collection);
        if (doc == null) {
            mongoTemplate.save(object, collection);
        }

    }

    private void deleteInsert(String collection, DBObject object) {
        mongoTemplate.remove(object);
        mongoTemplate.save(object, collection);
    }

}

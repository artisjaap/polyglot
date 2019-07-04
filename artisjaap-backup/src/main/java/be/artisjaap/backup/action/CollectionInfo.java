package be.artisjaap.backup.action;

import be.artisjaap.backup.action.to.CollectionInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CollectionInfo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<CollectionInfoTO> allCollections() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();

        return collectionNames.stream().map(this::convertTO).collect(Collectors.toList());


    }

    private CollectionInfoTO convertTO(String collectionName) {
        return CollectionInfoTO.newBuilder()
                .withCollectionName(collectionName)
                .build();
    }

}

package be.artisjaap.backup.action;

import be.artisjaap.backup.action.to.BackupCollectionConfigTO;
import be.artisjaap.backup.action.to.BackupConfigTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConfigBuilder {

    private final MongoTemplate mongoTemplate;

    private ConfigBuilder(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    public BackupConfigTO fullDbBackup() {
        List<BackupCollectionConfigTO> collectionConfigTOS = mongoTemplate.getCollectionNames()
                .stream()
                .map(collectionName -> BackupCollectionConfigTO.builder().clearAfterBackup(false).name(collectionName).build())
                .collect(Collectors.toList());

        return BackupConfigTO.builder()
                .config(collectionConfigTOS)
                .build();
    }

    public BackupConfigTO singleTableBackup(String tableName) {
        BackupCollectionConfigTO backupCollectionConfigTO = BackupCollectionConfigTO.builder().clearAfterBackup(false).name(tableName).build();

        return BackupConfigTO.builder()
               // .aConfig(backupCollectionConfigTO)
                .build();
    }


}

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

    @Autowired
    private MongoTemplate mongoTemplate;

    public BackupConfigTO fullDbBackup() {
        List<BackupCollectionConfigTO> collectionConfigTOS = mongoTemplate.getCollectionNames()
                .stream()
                .map(collectionName -> BackupCollectionConfigTO.newBuilder().withClearAfterBackup(false).withName(collectionName).build())
                .collect(Collectors.toList());

        return BackupConfigTO.newBuilder()
                .withConfig(collectionConfigTOS)
                .build();
    }

    public BackupConfigTO singleTableBackup(String tableName) {
        BackupCollectionConfigTO backupCollectionConfigTO = BackupCollectionConfigTO.newBuilder().withClearAfterBackup(false).withName(tableName).build();

        return BackupConfigTO.newBuilder()
                .addConfig(backupCollectionConfigTO)
                .build();
    }


}

package be.artisjaap.migrate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("be.artisjaap.migrate.model.mongo")
public class MigrateApplication {
}



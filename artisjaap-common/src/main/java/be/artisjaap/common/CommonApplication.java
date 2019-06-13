package be.artisjaap.common;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("be.artisjaap.common.model.mongo")
public class CommonApplication {
}

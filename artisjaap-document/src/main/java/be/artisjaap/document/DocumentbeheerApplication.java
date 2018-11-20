package be.artisjaap.document;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("be.artisjaap.document.model.mongo")
@SpringBootApplication
public class DocumentbeheerApplication {

}

package be.artisjaap.document;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories("be.artisjaap.document.model.mongo")
public class DocumentbeheerApplication {

}

package be.artisjaap.document;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("be.artisjaap.document")
@EnableMongoRepositories("be.artisjaap.document.model")
public class DocumentbeheerSpringContext {
}




package be.artisjaap.properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("be.artisjaap.properties")
@EnableMongoRepositories("be.artisjaap.properties.model.mongo")
public class PropertiesSpringContext {
}

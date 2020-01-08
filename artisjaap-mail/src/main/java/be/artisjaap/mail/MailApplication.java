package be.artisjaap.mail;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "be.artisjaap")
@EnableMongoRepositories("be.artisjaap.mail.model.mongo")
public class MailApplication {
}

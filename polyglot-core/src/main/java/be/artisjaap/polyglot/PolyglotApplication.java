package be.artisjaap.polyglot;

import be.artisjaap.backup.BackupApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("be.artisjaap.polyglot.core.model")
public class PolyglotApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PolyglotApplication.class)
                .sources(DocumentbeheerApplication.class)
                .sources(BackupApplication.class)
                .build()
                .run(args);


    }
}

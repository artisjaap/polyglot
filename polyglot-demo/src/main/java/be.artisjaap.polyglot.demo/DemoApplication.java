package be.artisjaap.polyglot.demo;

import be.artisjaap.angular.generator.AngularGeneratorApplication;
import be.artisjaap.backup.BackupApplication;
import be.artisjaap.backup.web.BackupWebApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.document.web.DocumentWebApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.swagger.PolyglotWebSwaggerApplication;
import be.artisjaap.polyglot.web.PolyglotWebApplication;
import be.artisjaap.properties.PropertiesApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .sources(PolyglotApplication.class)
                .sources(DocumentbeheerApplication.class)
                .sources(PolyglotWebApplication.class)
                .sources(PolyglotWebSwaggerApplication.class)
                .sources(MigrateApplication.class)
                .sources(AngularGeneratorApplication.class)
                .sources(DocumentWebApplication.class)
                .sources(BackupApplication.class)
                .sources(BackupWebApplication.class)
                .sources(MailApplication.class)
                .sources(PropertiesApplication.class)
                .build()
                .run(args);
    }
}

package be.artisjaap.polyglot.web;

import be.artisjaap.backup.BackupApplication;
import be.artisjaap.backup.web.BackupWebApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.document.web.DocumentWebApplication;
import be.artisjaap.i18n.I18nApplication;
import be.artisjaap.i18n.web.I18nWebApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.mail.web.MailWebApplication;
import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.web.configuration.WebConfig;
import be.artisjaap.properties.PropertiesApplication;
import be.artisjaap.properties.web.PropertiesWebApplication;
import be.artisjaap.schedule.ScheduleApplication;
import be.artisjaap.schedule.web.ScheduleWebApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PolyglotWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DocumentbeheerApplication.class)
                .sources(BackupApplication.class)
                .sources(BackupWebApplication.class)
                .sources(DocumentbeheerApplication.class)
                .sources(DocumentWebApplication.class)
                .sources(I18nApplication.class)
                .sources(I18nWebApplication.class)
                .sources(MailApplication.class)
                .sources(MailWebApplication.class)
                .sources(MigrateApplication.class)
                .sources(PolyglotApplication.class)
                .sources(PolyglotWebApplication.class)
                .sources(PropertiesApplication.class)
                .sources(PropertiesWebApplication.class)
                .sources(ScheduleApplication.class)
                .sources(ScheduleWebApplication.class)
                .sources(WebConfig.class)
                .build()
                .run(args);
//        SpringApplication.run(PolyglotWebApplication.class, args);
    }
}

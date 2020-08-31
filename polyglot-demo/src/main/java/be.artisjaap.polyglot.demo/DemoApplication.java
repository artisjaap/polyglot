package be.artisjaap.polyglot.demo;


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
import be.artisjaap.polyglot.swagger.PolyglotWebSwaggerApplication;
import be.artisjaap.polyglot.web.PolyglotWebApplication;
import be.artisjaap.polyglot.web.configuration.WebConfig;
import be.artisjaap.properties.PropertiesApplication;
import be.artisjaap.schedule.ScheduleApplication;
import be.artisjaap.schedule.web.ScheduleWebApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PolyglotWebApplication.class)
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .sources(PolyglotApplication.class)
                .sources(DocumentbeheerApplication.class)
                .sources(PolyglotWebApplication.class)
                .sources(PolyglotWebSwaggerApplication.class)
                .sources(MigrateApplication.class)
                .sources(DocumentWebApplication.class)
                .sources(BackupApplication.class)
                .sources(BackupWebApplication.class)
                .sources(MailApplication.class)
                .sources(MailWebApplication.class)
                .sources(PropertiesApplication.class)
                .sources(I18nApplication.class)
                .sources(I18nWebApplication.class)
                .sources(ScheduleApplication.class)
                .sources(ScheduleWebApplication.class)
                .sources(WebConfig.class)
                .build()
                .run(args);
    }

}

package be.artisjaap.polyglot.demo;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.swagger.PolyglotWebSwaggerApplication;
import be.artisjaap.polyglot.web.PolyglotWebApplication;
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
                .build()
                .run(args);
    }
}

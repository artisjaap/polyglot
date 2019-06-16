package be.artisjaap.polyglot.swagger;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.web.PolyglotWebApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PolyglotWebSwaggerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PolyglotApplication.class)
                .sources(DocumentbeheerApplication.class)
                .sources(PolyglotWebApplication.class)
                .sources(PolyglotWebSwaggerApplication.class)
                .build()
                .run(args);
    }
}



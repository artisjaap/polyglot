package be.artisjaap.document.web;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PolyglotDocumentWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PolyglotApplication.class)
                .sources(DocumentbeheerApplication.class)
                .build()
                .run(args);
    }
}

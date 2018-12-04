package be.artisjaap.polyglot.document.web;

import be.artisjaap.document.DocumentbeheerApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PolyglotDocumentWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DocumentbeheerApplication.class)
                .build()
                .run(args);
    }
}

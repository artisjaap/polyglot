package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.PolyglotApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PolyglotWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PolyglotApplication.class)
                .sources(PolyglotWebApplication.class)
                .build()
                .run(args);
//        SpringApplication.run(PolyglotWebApplication.class, args);
    }
}

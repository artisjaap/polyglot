package be.artisjaap.polyglot.web;

import be.artisjaap.angular.generator.AngularGeneratorApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.web.configuration.WebConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PolyglotWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PolyglotApplication.class)
                .sources(PolyglotWebApplication.class)
                .sources(AngularGeneratorApplication.class)
                .sources(WebConfig.class)
                .build()
                .run(args);
//        SpringApplication.run(PolyglotWebApplication.class, args);
    }
}

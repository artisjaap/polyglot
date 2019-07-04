package be.artisjaap.i18n.web;

import be.artisjaap.i18n.I18nApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class I18nWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(I18nApplication.class)
                .build()
                .run(args);
    }
}

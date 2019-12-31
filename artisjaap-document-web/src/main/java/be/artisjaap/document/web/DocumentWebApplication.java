package be.artisjaap.document.web;

import be.artisjaap.document.DocumentbeheerApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "be.artisjaap")
public class DocumentWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DocumentbeheerApplication.class)
                .build()
                .run(args);
    }
}

package be.artisjaap.backup.web;

import be.artisjaap.backup.BackupApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackupWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(BackupApplication.class)
                .build()
                .run(args);
    }

}

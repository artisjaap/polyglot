package be.artisjaap.schedule.web;

import be.artisjaap.schedule.ScheduleApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ScheduleWebApplication {


    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .sources(ScheduleApplication.class)
                .build()
                .run(args);


    }
}

package be.artisjaap.schedule;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
@EnableMongoRepositories("be.artisjaap.schedule.model.mongo")
public class ScheduleApplication {

    @Bean
    public TaskScheduler getTaskScheduler() {
        ScheduledExecutorService executor = getTaskSchedulerService();
        return new ConcurrentTaskScheduler(executor);
    }

    @Bean
    public ScheduledExecutorService getTaskSchedulerService() {
        return Executors.newScheduledThreadPool(1);
    }


    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .build()
                .run(args);


    }
}

package be.artisjaap.schedule.cucumber;

import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.schedule.ScheduleApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        ScheduleApplication.class, MigrateApplication.class})
public class ScheduleInMemoryTestParent {
}

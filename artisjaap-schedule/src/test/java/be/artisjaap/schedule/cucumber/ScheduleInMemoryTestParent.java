package be.artisjaap.schedule.cucumber;

import be.artisjaap.schedule.ScheduleApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        ScheduleApplication.class})
public class ScheduleInMemoryTestParent {
}

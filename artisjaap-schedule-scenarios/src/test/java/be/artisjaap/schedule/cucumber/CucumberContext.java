package be.artisjaap.schedule.cucumber;

import be.artisjaap.common.CommonApplication;
import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.schedule.ScheduleApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
@ContextConfiguration(classes = {
        CommonApplication.class, ScheduleApplication.class, MigrateApplication.class})
public class CucumberContext {

}

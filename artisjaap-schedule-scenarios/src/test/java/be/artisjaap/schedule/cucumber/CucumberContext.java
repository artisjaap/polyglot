package be.artisjaap.schedule.cucumber;

import be.artisjaap.common.CommonApplication;
import be.artisjaap.migrate.MigrateApplication;
import be.artisjaap.schedule.ScheduleApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
@ContextConfiguration(classes = {
        CommonApplication.class, ScheduleApplication.class, MigrateApplication.class})
public class CucumberContext {

}

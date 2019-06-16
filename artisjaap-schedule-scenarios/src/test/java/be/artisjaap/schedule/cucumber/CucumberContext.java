package be.artisjaap.schedule.cucumber;

import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CucumberContext extends ScheduleInMemoryTestParent {

    @Before
    public void init() {

    }
}

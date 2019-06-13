package be.artisjaap.schedule.cucumber;

import be.artisjaap.common.service.Context;
import be.artisjaap.schedule.action.ListTasks;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScheduleStepDefinition {
    @Autowired
    private ListTasks listTasks;

    @Given("^there is ([0-9]*) task scheduled$")
    public void countNumberOfScheduledTasks(int number){
        assertThat(listTasks.allTasks().size(), is(number));
    }
}

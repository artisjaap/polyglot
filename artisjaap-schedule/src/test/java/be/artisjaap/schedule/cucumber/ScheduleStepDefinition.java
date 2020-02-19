package be.artisjaap.schedule.cucumber;

import be.artisjaap.schedule.action.ListTasks;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScheduleStepDefinition{
    @Autowired
    private ListTasks listTasks;

    @Given("A schedule service")
    public void aScheduleService(){

    }

    @Given("there is {int} task scheduled")
    public void countNumberOfScheduledTasks(int number){
        assertThat(listTasks.allTasks().size(), is(number));
    }
}

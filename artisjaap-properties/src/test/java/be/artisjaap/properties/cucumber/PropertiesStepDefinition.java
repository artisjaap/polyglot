package be.artisjaap.properties.cucumber;

import be.artisjaap.properties.action.GetProperty;
import be.artisjaap.properties.action.SetProperty;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertiesStepDefinition {
    @Autowired
    private GetProperty getProperty;

    @Autowired
    private SetProperty setProperty;

    @Given("A properties service")
    public void aPropertiesService(){

    }

    @Given("^a property (.*) with value (.*)$")
    public void createAProperty(String key, String value) {
        setProperty.forKeyWithValue(key, value);
    }

    @Then("^when I request (.*) it has value (.*)$")
    public void whenIRequestPropItHasValueVal(String key, String value) {
        String valFromDB = getProperty.valueForKey(key);
        Assert.assertThat(valFromDB, CoreMatchers.is(value));
    }

    @When("^the property (.*) is updated to (.*)$")
    public void thePropertyPropIsUpdatedToValue(String key, String value) {
        createAProperty(key, value);
    }
}

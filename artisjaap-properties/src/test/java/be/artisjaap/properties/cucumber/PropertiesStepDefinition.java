package be.artisjaap.properties.cucumber;

import be.artisjaap.properties.action.GetProperty;
import be.artisjaap.properties.action.SetProperty;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
public class PropertiesStepDefinition {
    @Autowired
    private GetProperty getProperty;

    @Autowired
    private SetProperty setProperty;

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

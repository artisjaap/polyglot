package be.artisjaap.properties.cucumber;

import be.artisjaap.properties.PropertiesInMemoryTestParent;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CucumberContext extends PropertiesInMemoryTestParent {
    @Before
    public void init() {

    }
}

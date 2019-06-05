package be.artisjaap.properties.cucumber;

import be.artisjaap.properties.PropertiesInMemoryTestParent;
import be.artisjaap.properties.action.SetProperty;
import cucumber.api.junit.Cucumber;
import cucumber.api.java.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
public class CucumberHooks extends PropertiesInMemoryTestParent {
    @Autowired
    private SetProperty setProperty;

    @Before
    public void clear() {
        System.out.println("BEFORE");
    }


}

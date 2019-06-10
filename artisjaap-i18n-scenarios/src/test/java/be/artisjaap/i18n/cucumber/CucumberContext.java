package be.artisjaap.i18n.cucumber;

import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CucumberContext extends I18nInMemoryTestParent{
    @Before
    public void init() {

    }
}

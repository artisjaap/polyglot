package be.artisjaap.mail.cucumber;

import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CucumberContext extends MailInMemoryTestParent{

    @Before
    public void init() {

    }
}

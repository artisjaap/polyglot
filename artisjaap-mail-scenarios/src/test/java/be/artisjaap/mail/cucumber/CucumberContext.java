package be.artisjaap.mail.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
public class CucumberContext {

}

package be.artisjaap.document.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap", plugin = {"html:c:/temp/cucumber", "json"})
public class CucumberContext {


}

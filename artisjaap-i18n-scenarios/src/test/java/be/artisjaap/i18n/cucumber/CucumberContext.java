package be.artisjaap.i18n.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
public class CucumberContext {

}

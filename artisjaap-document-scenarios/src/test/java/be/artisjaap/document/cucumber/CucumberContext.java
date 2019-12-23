package be.artisjaap.document.cucumber;

import be.artisjaap.common.CommonApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
public class CucumberContext {


}

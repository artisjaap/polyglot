package be.artisjaap.polyglot.cucumber;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.properties.PropertiesApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true
, features="classpath:scenarios"
, glue = "be.artisjaap"
, dryRun = false
, plugin={"html:build/reports/cucumber", "pretty", "json:build/reports/cucumber/report.json", "be.artisjaap.polyglot.cucumber.PrettyReport:build/reports/cucumber"})
public class CucumberContext {

}

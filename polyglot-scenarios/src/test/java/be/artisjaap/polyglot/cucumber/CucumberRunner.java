package be.artisjaap.polyglot.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true
, features="classpath:feature/"
, glue = "be.artisjaap.polyglot"
, dryRun = false
, plugin={"html:build/reports/cucumber", "pretty", "json:build/reports/cucumber/report.json", "be.artisjaap.polyglot.cucumber.PrettyReport:build/reports/cucumber"})
public class CucumberRunner {

}

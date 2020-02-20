package be.artisjaap.polyglot.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios"
        , tags = {"@Only"}
        , extraGlue = {"be.artisjaap.document.cucumber", "be.artisjaap.common.cucumber", "be.artisjaap.backup.cucumber",
        "be.artisjaap.i18n.cucumber", "be.artisjaap.mail.cucumber", "be.artisjaap.properties.cucumber", "be.artisjaap.schedule.cucumber"}
        /*, plugin = {"html:build/reports/cucumber", "pretty", "json:build/reports/cucumber/report.json", "be.artisjaap.polyglot.cucumber.PrettyReport:build/reports/cucumber"}*/)
public class CucumberContext {

}

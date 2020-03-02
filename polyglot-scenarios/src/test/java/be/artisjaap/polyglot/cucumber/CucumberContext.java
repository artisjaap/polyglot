package be.artisjaap.polyglot.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios"
        , tags = {"@Only"}
        , extraGlue = {"be.artisjaap.document.cucumber", "be.artisjaap.common.cucumber", "be.artisjaap.backup.cucumber",
        "be.artisjaap.i18n.cucumber", "be.artisjaap.mail.cucumber", "be.artisjaap.properties.cucumber", "be.artisjaap.schedule.cucumber"}
        , plugin = {"pretty", "json:target/report.json", "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"})
public class CucumberContext {

}

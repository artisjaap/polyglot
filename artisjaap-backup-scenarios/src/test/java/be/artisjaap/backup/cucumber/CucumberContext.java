package be.artisjaap.backup.cucumber;

import be.artisjaap.backup.BackupApplication;
import be.artisjaap.common.CommonApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.properties.PropertiesApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:scenarios", glue = "be.artisjaap")
public class CucumberContext {

}

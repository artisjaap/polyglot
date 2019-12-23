package be.artisjaap.backup.cucumber;

import be.artisjaap.backup.BackupApplication;
import be.artisjaap.common.CommonApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.properties.PropertiesApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {BackupApplication.class, CommonApplication.class, MailApplication.class, PropertiesApplication.class})
public abstract class CucumberInMemoryTestParent {
}

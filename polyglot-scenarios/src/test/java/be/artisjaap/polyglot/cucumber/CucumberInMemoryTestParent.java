package be.artisjaap.polyglot.cucumber;

import be.artisjaap.backup.BackupApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.properties.PropertiesApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {PolyglotApplication.class,
        DocumentbeheerApplication.class,
        PropertiesApplication.class,
        BackupApplication.class,
        MailApplication.class})
public class CucumberInMemoryTestParent {
}

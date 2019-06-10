package be.artisjaap.mail.cucumber;

import be.artisjaap.mail.MailApplication;
import be.artisjaap.properties.PropertiesApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        MailApplication.class, PropertiesApplication.class})
public class MailInMemoryTestParent {
}

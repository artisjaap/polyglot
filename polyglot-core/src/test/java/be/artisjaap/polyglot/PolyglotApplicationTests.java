package be.artisjaap.polyglot;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.mail.MailApplication;
import be.artisjaap.properties.PropertiesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        DocumentbeheerApplication.class})
public class PolyglotApplicationTests {


    @Test
    public void contextLoads() {
    }

}

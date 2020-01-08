package be.artisjaap.mail.cucumber;

import be.artisjaap.mail.action.SaveMailTemplate;
import be.artisjaap.mail.action.to.MailTemplateTO;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class MailStepDefinition {
    @Autowired
    private SaveMailTemplate saveMailTemplate;

    @Given("^A mail template with code (.*), subject: \"([^\"]*)\" and body$")
    public void aMailTemplateWithCodeCODESubjectAndBody(String code, String subject, String body) {

        saveMailTemplate.forTemplate(MailTemplateTO.builder()
                .code(code)
                .subject(subject)
                .htmlBody(body)
                .build());

    }
}

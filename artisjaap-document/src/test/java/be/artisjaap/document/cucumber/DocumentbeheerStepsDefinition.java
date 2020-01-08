package be.artisjaap.document.cucumber;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.*;
import be.artisjaap.document.action.to.*;
import cucumber.api.java.en.When;

import cucumber.api.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DocumentbeheerStepsDefinition {

    @Autowired
    private AddSimpleTemplate addSimpleTemplate;

    @Autowired
    private AddCombinedTemplate addCombinedTemplate;

    @Autowired
    private ActivateSimpleOrCombinedTemplate activateSimpleTemplate;

    @Autowired
    private ActivateDocument activateDocument;

    @Autowired
    private FindAvailableSimpleTemplates findAvailableSimpleTemplates;

    @Autowired
    private FindAvailableCombinedTemplates findAvailableCombinedTemplates;

    @Autowired
    private FindAvailableDocuments findAvailableDocuments;

    @Autowired
    private AddDocument addDocument;


    @Given("^an active template (.*) with code (.*) in language (.*)$")
    public void eenBriefMetCodeInTaalDieActiefIs(String bestand, String code, String isoCode) {
        eenTemplateWordtOpgeladen(bestand, code, isoCode);
        deTemplateMetCodeActiefWordtGezet(code, isoCode);
    }


    @When("^a template (.*) is uploaded with code (.*) in language (.*)$")
    public void eenTemplateWordtOpgeladen(String bestand, String code, String isoCode) {
        TemplateNewTO templateTO = TemplateNewTO.builder()
                .code(code)
                .originalFilename(bestand)
                .taal(isoCode)
                .template(bytesFromFile(bestand))
                .build();
        addSimpleTemplate.forNew(templateTO);
    }

    @When("^template with code (.*) is activated in language (.*)$")
    public void deTemplateMetCodeActiefWordtGezet(String code, String isoCode) {
        List<TemplateTO> templateTOS = findAvailableSimpleTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(templateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(templateTOS.get(0).getId());
    }

    @When("^the combined template with code (.*) is activated in language (.*)$")
    public void deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(String code, String isoCode) throws Throwable {
        List<CombinedTemplateTO> combinedTemplateTOS = findAvailableCombinedTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(combinedTemplateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(combinedTemplateTOS.get(0).getId());
    }

    @When("^the document with code (.*) has been set active in language (.*)$")
    public void deBriefMANDAAT_BRIEFWordtActiefGezet(String code, String isoCode) throws Throwable {
        List<DocumentTO> documentTOS = findAvailableDocuments.withCodeAndLanguage(code, isoCode);
        assertThat(documentTOS.size(), is(1));
        activateDocument.metId(documentTOS.get(0).getId());
    }

    @When("^a combined template with code (.*) in language (.*) consists of templates (.*)$")
    public void eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(String code, String taal, List<String> templates) {
        CombinedTemplateNewTO combinatie = CombinedTemplateNewTO
                .builder()
                .code(code)
                .templates(templates)
                .taal(taal)
                .build();
        addCombinedTemplate.from(combinatie);
    }

    private byte[] bytesFromFile(String bestand) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("templates/" + bestand).getFile());
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }



    @When("^a document (.*) in language (.*) exists of templates (.*)")
    public void eenBriefInNldDieBestaatUitDeTemplates(String code, String isoCode, List<String> templates) throws Throwable {
        DocumentNewTO nieuweBrief = DocumentNewTO.builder()
                .code(code)
                .taal(isoCode)
                .templates(templates)
                .build();
        addDocument.forNew(nieuweBrief);
    }


    @Given("^vandaag is (\\d{2}-\\d{2}-\\d{4}) ([0-9]{2}):([0-9]{2})$")
    public void vandaagIs(String datumVanGebeurtenis, int uur, int min) {
        LocalDate localDate = LocalDateUtils.parseDateFromDDMMYYYYString(datumVanGebeurtenis);
        LocalDateUtils.useFixedDate(localDate.atTime(uur, min, 0));
    }
}

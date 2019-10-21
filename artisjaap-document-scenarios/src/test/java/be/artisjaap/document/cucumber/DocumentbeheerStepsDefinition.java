package be.artisjaap.document.cucumber;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.*;
import be.artisjaap.document.action.to.*;
import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Gegeven;
import cucumber.api.junit.Cucumber;
import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

//@RunWith(Cucumber.class)
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


    @Gegeven("^een template (.*) met code (.*) in (.*)$")
    public void eenTemplateMetCodeInTaal(String bestand, String code, String isoCode) {
        eenTemplateWordtOpgeladen(bestand, code, isoCode);
    }

    @Gegeven("^een actieve template (.*) met code (.*) in (.*)$")
    public void eenBriefMetCodeInTaalDieActiefIs(String bestand, String code, String isoCode) {
        eenTemplateWordtOpgeladen(bestand, code, isoCode);
        deTemplateMetCodeActiefWordtGezet(code, isoCode);
    }


    @Als("^een template (.*) wordt opgeladen als code (.*) in (.*)$")
    public void eenTemplateWordtOpgeladen(String bestand, String code, String isoCode) {
        TemplateNewTO templateTO = TemplateNewTO.newBuilder()
                .withCode(code)
                .withOriginalFilename(bestand)
                .withTaal(isoCode)
                .withTemplate(bytesFromFile(bestand))
                .build();
        addSimpleTemplate.forNew(templateTO);
    }

    @Als("^de template met code (.*) actief wordt gezet in taal (.*)$")
    public void deTemplateMetCodeActiefWordtGezet(String code, String isoCode) {
        List<TemplateTO> templateTOS = findAvailableSimpleTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(templateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(templateTOS.get(0).getId());
    }

    @Als("^de gecombineerde template met code (.*) actief wordt gezet in taal (.*)$")
    public void deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(String code, String isoCode) throws Throwable {
        List<CombinedTemplateTO> combinedTemplateTOS = findAvailableCombinedTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(combinedTemplateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(combinedTemplateTOS.get(0).getId());
    }

    @Als("^de brief met code (.*) actief wordt gezet in taal (.*)$")
    public void deBriefMANDAAT_BRIEFWordtActiefGezet(String code, String isoCode) throws Throwable {
        List<DocumentTO> documentTOS = findAvailableDocuments.withCodeAndLanguage(code, isoCode);
        assertThat(documentTOS.size(), is(1));
        activateDocument.metId(documentTOS.get(0).getId());
    }

    @Als("^een gecombineerde template met code (.*) in taal (.*) wordt gemaakt uit de templates (.*)$")
    public void eenGecombineerdeTemplateMetCodeWordtGemaaktUitDeTemplates(String code, String taal, List<String> templates) {
        CombinedTemplateNewTO combinatie = CombinedTemplateNewTO
                .newBuilder()
                .withCode(code)
                .withTemplates(templates)
                .withTaal(taal)
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



    @Als("^een brief (.*) in (.*) die bestaat uit de templates (.*)")
    public void eenBriefInNldDieBestaatUitDeTemplates(String code, String isoCode, List<String> templates) throws Throwable {
        DocumentNewTO nieuweBrief = DocumentNewTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withTemplates(templates)
                .build();
        addDocument.forNew(nieuweBrief);
    }


    @Gegeven("^vandaag is (\\d{2}-\\d{2}-\\d{4}) ([0-9]{2}):([0-9]{2})$")
    public void vandaagIs(String datumVanGebeurtenis, int uur, int min) {
        LocalDate localDate = LocalDateUtils.parseDateFromDDMMYYYYString(datumVanGebeurtenis);
        LocalDateUtils.useFixedDate(localDate.atTime(uur, min, 0));
    }
}

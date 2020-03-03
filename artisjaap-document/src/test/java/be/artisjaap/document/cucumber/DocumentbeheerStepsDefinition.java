package be.artisjaap.document.cucumber;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddCombinedTemplate;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.AddSimpleTemplate;
import be.artisjaap.document.action.FindAvailableCombinedTemplates;
import be.artisjaap.document.action.FindAvailableDocuments;
import be.artisjaap.document.action.FindAvailableSimpleTemplates;
import be.artisjaap.document.action.to.CombinedTemplateNewTO;
import be.artisjaap.document.action.to.CombinedTemplateTO;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.action.to.TemplateNewTO;
import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.model.Document;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DocumentbeheerStepsDefinition {

    private final static String templateBaseResourceFolder = "templates/";

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

    @Autowired
    private DocumentWorld documentWorld;

    @Given("A document service")
    public void aDocumentService(){
        documentWorld.save("doc", Document.newBuilder().build());
        documentWorld.increase();
    }

    @Given("an active template {file} with code {code} in language {language}")
    public void eenBriefMetCodeInTaalDieActiefIs(String bestand, String code, String isoCode) {
        eenTemplateWordtOpgeladen(bestand, code, isoCode);
        deTemplateMetCodeActiefWordtGezet(code, isoCode);
    }


    @When("a template {file} is uploaded with code {code} in language {language}")
    public void eenTemplateWordtOpgeladen(String bestand, String code, String isoCode) {
        TemplateNewTO templateTO = TemplateNewTO.builder()
                .code(code)
                .originalFilename(bestand)
                .taal(isoCode)
                .template(bytesFromFile(bestand))
                .build();
        addSimpleTemplate.forNew(templateTO);
    }

    @When("template with code {code} is activated in language {language}")
    public void deTemplateMetCodeActiefWordtGezet(String code, String isoCode) {
        List<TemplateTO> templateTOS = findAvailableSimpleTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(templateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(templateTOS.get(0).getId());
    }

    @When("the combined template with code {code} is activated in language {language}")
    public void deGecombineerdeTemplateMetCodeActiefWordtGezetInTaal(String code, String isoCode) throws Throwable {
        List<CombinedTemplateTO> combinedTemplateTOS = findAvailableCombinedTemplates.withCodeAndLanguage(code, isoCode);
        assertThat(combinedTemplateTOS.size(), is(1));
        activateSimpleTemplate.activateTemplate(combinedTemplateTOS.get(0).getId());
    }

    @When("the document with code {code} has been set active in language {language}")
    public void deBriefMANDAAT_BRIEFWordtActiefGezet(String code, String isoCode) throws Throwable {
        List<DocumentTO> documentTOS = findAvailableDocuments.withCodeAndLanguage(code, isoCode);
        assertThat(documentTOS.size(), is(1));
        activateDocument.metId(documentTOS.get(0).getId());
    }
//         a combined template with code MANDAA in language nld        consists of templates MANDAAT_HEADER_FOOTER, MANDAAT_BRIEF_NA_TELEFONISCH_CONTACT
    @When("a combined template with code {code} in language {language} consists of templates {listOfCodes}")
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
        File file = new File(classLoader.getResource(templateBaseResourceFolder + bestand).getFile());
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    @When("a document {code} in language {language} exists of templates {listOfCodes}")
    public void eenBriefInNldDieBestaatUitDeTemplates(String code, String isoCode, List<String> templates) throws Throwable {
        DocumentNewTO nieuweBrief = DocumentNewTO.builder()
                .code(code)
                .taal(isoCode)
                .templates(templates)
                .build();
        addDocument.forNew(nieuweBrief);
    }


    @Given("today is {dateAndTime}")
    public void vandaagIs(LocalDateTime dateAndTime) {
        LocalDateUtils.useFixedDate(dateAndTime);
    }
}

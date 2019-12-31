package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.AutofindTemplateType;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class BriefPersister {

    @Resource
    private AddDocument addDocument;

    @Resource
    private TemplatePersister templatePersister;

    @Autowired
    private AutofindTemplateType autofindTemplateType;

    @Autowired
    private GecombineerdeTemplatePersister gecombineerdeTemplatePersister;


    @Autowired
    private ActivateDocument activateDocument;

    public void maakEenBrief(String code){
        templatePersister.maakTemplate("STANDAARD_HEADER_FOOTER", "/templates/STANDAARD_HEADER_FOOTER.DOCX");
        templatePersister.maakTemplate("TEST_TEMPLATE", "/templates/MANDAAT_ACTIELIJST_RAPPEL-PROCEDURE_NL.DOCX");
        templatePersister.maakTemplate("MANDAAT_STOPZETTING", "/templates/MANDAAT_ACTIELIJST_RAPPEL-PROCEDURE_NL.DOCX");
        gecombineerdeTemplatePersister.maakGecombineerdeTemplate("TEST_MET_HEADER_FOOTER", "STANDAARD_HEADER_FOOTER", "TEST_TEMPLATE");

        DocumentNewTO briefTO = DocumentNewTO.builder()
                .code(code)
                .templates(Arrays.asList("TEST_MET_HEADER_FOOTER", "MANDAAT_STOPZETTING"))
                .taal("DUTCH")

                .build();
        DocumentTO gemaakteBrief = addDocument.forNew(briefTO);
        activateDocument.metId(gemaakteBrief.getId());


    }


    public void maakEenSimpleBrief(String code){
        templatePersister.maakTemplate("STANDAARD_HEADER_FOOTER", "templates/STANDAARD_HEADER_FOOTER.DOCX");

        DocumentNewTO briefTO = DocumentNewTO.builder()
                .code(code)
                .templates(Arrays.asList("STANDAARD_HEADER_FOOTER"))
                .taal("DUTCH")
                .build();
        addDocument.forNew(briefTO);
    }
}

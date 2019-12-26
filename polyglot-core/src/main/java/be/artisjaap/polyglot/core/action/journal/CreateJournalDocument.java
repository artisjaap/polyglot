package be.artisjaap.polyglot.core.action.journal;

import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddSimpleTemplate;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.action.to.TemplateNewTO;
import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Component
public class CreateJournalDocument {

    @Autowired
    private AddSimpleTemplate addSimpleTemplate;

    @Autowired
    private ActivateSimpleOrCombinedTemplate activateSimpleTemplate;

    @Autowired
    private AddDocument addDocument;

    @Autowired
    private ActivateDocument activateDocument;

    public void createOfNotExists(){
        File file = new File(this.getClass().getClassLoader().getResource("documents/JournalReport.docx").getFile());
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        TemplateTO templateTO = addSimpleTemplate.forNew(TemplateNewTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withOriginalFilename("JournalReports.docx")
                .withTaal("DUTCH")
                .withTemplate(bytes)
                .build());

        activateSimpleTemplate.activateTemplate(templateTO.getId());

        DocumentTO documentTO = addDocument.forNew(DocumentNewTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withTaal("DUTCH")
                .withTemplates(Arrays.asList(templateTO.getCode()))
                .build());

        activateDocument.metId(documentTO.getId());
    }
}

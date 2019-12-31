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

    private final AddSimpleTemplate addSimpleTemplate;
    private final ActivateSimpleOrCombinedTemplate activateSimpleTemplate;
    private final AddDocument addDocument;
    private final ActivateDocument activateDocument;

    public CreateJournalDocument(AddSimpleTemplate addSimpleTemplate, ActivateSimpleOrCombinedTemplate activateSimpleTemplate, AddDocument addDocument, ActivateDocument activateDocument) {
        this.addSimpleTemplate = addSimpleTemplate;
        this.activateSimpleTemplate = activateSimpleTemplate;
        this.addDocument = addDocument;
        this.activateDocument = activateDocument;
    }

    public void createOfNotExists(){
        File file = new File(this.getClass().getClassLoader().getResource("documents/JournalReport.docx").getFile());
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        TemplateTO templateTO = addSimpleTemplate.forNew(TemplateNewTO.builder()
                .code(DocumentCode.REPORT_FOR_JOURNAL.name())
                .originalFilename("JournalReports.docx")
                .taal("DUTCH")
                .template(bytes)
                .build());

        activateSimpleTemplate.activateTemplate(templateTO.getId());

        DocumentTO documentTO = addDocument.forNew(DocumentNewTO.builder()
                .code(DocumentCode.REPORT_FOR_JOURNAL.name())
                .taal("DUTCH")
                .templates(Arrays.asList(templateTO.getCode()))
                .build());

        activateDocument.metId(documentTO.getId());
    }
}

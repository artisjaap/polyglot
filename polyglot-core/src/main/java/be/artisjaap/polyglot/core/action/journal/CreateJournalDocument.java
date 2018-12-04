package be.artisjaap.polyglot.core.action.journal;

import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.ActivateTemplate;
import be.artisjaap.document.action.AddTemplate;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.to.BriefNieuwTO;
import be.artisjaap.document.action.to.BriefTO;
import be.artisjaap.document.action.to.TemplateNieuwTO;
import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.polyglot.core.action.documebts.docconfig.DocumentCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Component
public class CreateJournalDocument {

    @Autowired
    private AddTemplate addTemplate;

    @Autowired
    private ActivateTemplate activateTemplate;

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

        TemplateTO templateTO = addTemplate.voor(TemplateNieuwTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withOriginalFilename("JournalReports.docx")
                .withTaal("DUTCH")
                .withTemplate(bytes)
                .build());

        activateTemplate.activeerTemplate(templateTO.getId());

        BriefTO briefTO = addDocument.voor(BriefNieuwTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withTaal("DUTCH")
                .withTemplates(Arrays.asList(templateTO.getCode()))
                .build());

        activateDocument.metId(briefTO.getId());
    }
}

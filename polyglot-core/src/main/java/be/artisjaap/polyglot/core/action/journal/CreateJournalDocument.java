package be.artisjaap.polyglot.core.action.journal;

import be.artisjaap.document.action.ActiveerBrief;
import be.artisjaap.document.action.ActiveerTemplate;
import be.artisjaap.document.action.EenvoudigeTemplateToevoegen;
import be.artisjaap.document.action.MaakBrief;
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
    private EenvoudigeTemplateToevoegen eenvoudigeTemplateToevoegen;

    @Autowired
    private ActiveerTemplate activeerTemplate;

    @Autowired
    private MaakBrief maakBrief;

    @Autowired
    private ActiveerBrief activeerBrief;

    public void createOfNotExists(){
        File file = new File(this.getClass().getClassLoader().getResource("documents/JournalReport.docx").getFile());
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        TemplateTO templateTO = eenvoudigeTemplateToevoegen.voor(TemplateNieuwTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withOriginalFilename("JournalReports.docx")
                .withTaal("DUTCH")
                .withTemplate(bytes)
                .build());

        activeerTemplate.activeerTemplate(templateTO.getId());

        BriefTO briefTO = maakBrief.voor(BriefNieuwTO.newBuilder()
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .withTaal("DUTCH")
                .withTemplates(Arrays.asList(templateTO.getCode()))
                .build());

        activeerBrief.metId(briefTO.getId());
    }
}

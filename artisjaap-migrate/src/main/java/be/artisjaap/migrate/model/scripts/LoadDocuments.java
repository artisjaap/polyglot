package be.artisjaap.migrate.model.scripts;

import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.AddSimpleTemplate;
import be.artisjaap.document.action.to.BriefCodeNieuwTO;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.action.to.TemplateCodeTO;
import be.artisjaap.document.action.to.TemplateNewTO;
import be.artisjaap.document.action.to.TemplateTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadDocuments extends AbstractInitScript {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Resource
    private AddDocument addDocument;
    @Resource
    private AddSimpleTemplate addSimpleTemplate;
    @Resource
    private ActivateDocument activateDocument;
    @Resource
    private ActivateSimpleOrCombinedTemplate activateSimpleOrCombinedTemplate;
    @Resource
    private DocumentLoader documentLoader;

    @Override
    public String omschrijving() {
        return "Load DOCX in DB";
    }

    @Override
    public String getVersion() {
        return "0.2";
    }

    @Override
    public Integer cardinality() {
        return 10;
    }

    @Override
    public void execute() {
        documentLoader.forConfig(DocumentLoaderConfigTO.builder()
                .documentCode("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .description("Word practice sheet with answer page")
                .language("NL")
                .templates(Arrays.asList(
                        TemplateLoaderConfigTO.builder()
                                .templateCode("WORD_PRACTICE_TEMPLATE")
                                .description("create printable pdf to practice words")
                                .documentPath("documents/word-practice-template.docx")
                                .build(),
                        TemplateLoaderConfigTO.builder()
                                .templateCode("WORD_PRACTICE_ANSWERS_TEMPLATE")
                                .description("create printable pdf to practice words")
                                .documentPath("documents/word-practice-answers-template.docx")
                                .build()))
                .build());


        documentLoader.forConfig(DocumentLoaderConfigTO.builder()
                .documentCode("JOURNAL_TRANSLATION_REPORT")
                .description("Overview of practiced words")
                .language("NL")
                .templates(Arrays.asList(TemplateLoaderConfigTO.builder()
                        .templateCode("JOURNAL_TRANSLATIONS")
                        .description("Journal translations")
                        .documentPath("documents/journal-translations-template.docx")
                        .build()))
                .build());

    }


}

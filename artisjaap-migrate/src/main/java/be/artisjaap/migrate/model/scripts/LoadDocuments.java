package be.artisjaap.migrate.model.scripts;

import be.artisjaap.document.action.ActivateDocument;
import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddDocument;
import be.artisjaap.document.action.AddSimpleTemplate;
import be.artisjaap.document.action.to.*;
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
        TemplateCodeTO wordPracticeTemplate = addSimpleTemplate.withNewCode(TemplateCodeNewTO.newBuilder()
                .withCode("WORD_PRACTICE_TEMPLATE")
                .withDescription("create printable pdf to practice words")
                .build());

        try {
            TemplateTO templateTO = addSimpleTemplate.forNew(TemplateNewTO.newBuilder()
                    .withCode("WORD_PRACTICE_TEMPLATE")
                    .withTaal("NL")
                    .withTemplate(FileUtils.readFileToByteArray(new File(getClass().getClassLoader().getResource("documents/word-practice-template.docx").getFile())))
                    .withOriginalFilename("word-practice-template.docx")
                    .build());
            activateSimpleOrCombinedTemplate.activateTemplate(templateTO.getId());
        }catch (Exception e){
            logger.error("Could not load WORD_PRACTICE_TEMPLATE");
        }

        TemplateCodeTO wordPracticeAnswersTemplate = addSimpleTemplate.withNewCode(TemplateCodeNewTO.newBuilder()
                .withCode("WORD_PRACTICE_ANSWERS_TEMPLATE")
                .withDescription("create printable pdf to practice words")
                .build());

        try {
            TemplateTO templateTO = addSimpleTemplate.forNew(TemplateNewTO.newBuilder()
                    .withCode("WORD_PRACTICE_ANSWERS_TEMPLATE")
                    .withTaal("NL")
                    .withTemplate(FileUtils.readFileToByteArray(new File(getClass().getClassLoader().getResource("documents/word-practice-answers-template.docx").getFile())))
                    .withOriginalFilename("word-practice-answers-template.docx")
                    .build());
            activateSimpleOrCombinedTemplate.activateTemplate(templateTO.getId());

        }catch (Exception e){
            logger.error("Could not load WORD_PRACTICE_ANSWERS_TEMPLATE");
        }


        addDocument.metNieuweCode(BriefCodeNieuwTO.newBuilder()
                .withCode("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .withDescription("Practice words with answers")
                .build());

        DocumentTO documentTO = addDocument.forNew(DocumentNewTO.newBuilder()
                .withCode("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .withTaal("NL")
                .withTemplates(Arrays.asList("WORD_PRACTICE_TEMPLATE", "WORD_PRACTICE_ANSWERS_TEMPLATE"))
                .build());
        activateDocument.metId(documentTO.getId());

    }
}

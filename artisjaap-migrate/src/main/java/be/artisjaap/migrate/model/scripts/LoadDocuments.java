package be.artisjaap.migrate.model.scripts;

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
        TemplateCodeTO wordPracticeTemplate = addSimpleTemplate.withNewCode(TemplateCodeNewTO.builder()
                .code("WORD_PRACTICE_TEMPLATE")
                .description("create printable pdf to practice words")
                .build());

        try {
            addSimpleTemplate.forNew(TemplateNewTO.builder()
                    .code("WORD_PRACTICE_TEMPLATE")
                    .taal("NL")
                    .template(FileUtils.readFileToByteArray(new File(getClass().getClassLoader().getResource("documents/word-practice-template.docx").getFile())))
                    .originalFilename("word-practice-template.docx")
                    .build());
        }catch (Exception e){
            logger.error("Could not load WORD_PRACTICE_TEMPLATE");
        }

        TemplateCodeTO wordPracticeAnswersTemplate = addSimpleTemplate.withNewCode(TemplateCodeNewTO.builder()
                .code("WORD_PRACTICE_ANSWERS_TEMPLATE")
                .description("create printable pdf to practice words")
                .build());

        try {
            addSimpleTemplate.forNew(TemplateNewTO.builder()
                    .code("WORD_PRACTICE_ANSWERS_TEMPLATE")
                    .taal("NL")
                    .template(FileUtils.readFileToByteArray(new File(getClass().getClassLoader().getResource("documents/word-practice-answers-template.docx").getFile())))
                    .originalFilename("word-practice-answers-template.docx")
                    .build());
        }catch (Exception e){
            logger.error("Could not load WORD_PRACTICE_ANSWERS_TEMPLATE");
        }

        addDocument.metNieuweCode(BriefCodeNieuwTO.builder()
                .code("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .description("Practice words with answers")
                .build());

        addDocument.forNew(DocumentNewTO.builder()
                .code("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .taal("NL")
                .templates(Arrays.asList("WORD_PRACTICE_TEMPLATE", "WORD_PRACTICE_ANSWERS_TEMPLATE"))
                .build());

    }
}

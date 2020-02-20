package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefCodeNieuwTO;
import be.artisjaap.document.action.to.DocumentLoaderConfigTO;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.action.to.TemplateLoaderConfigTO;
import be.artisjaap.document.action.to.TemplateNewTO;
import be.artisjaap.document.action.to.TemplateTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DocumentLoader {

    private AddSimpleTemplate addSimpleTemplate;
    private ActivateSimpleOrCombinedTemplate activateSimpleOrCombinedTemplate;
    private AddDocument addDocument;
    private ActivateDocument activateDocument;

    public DocumentLoader(AddSimpleTemplate addSimpleTemplate, ActivateSimpleOrCombinedTemplate activateSimpleOrCombinedTemplate, AddDocument addDocument, ActivateDocument activateDocument) {

        this.addSimpleTemplate = addSimpleTemplate;
        this.activateSimpleOrCombinedTemplate = activateSimpleOrCombinedTemplate;
        this.addDocument = addDocument;
        this.activateDocument = activateDocument;
    }

    public void forConfig(DocumentLoaderConfigTO config) {
        config.getTemplates().forEach(templateLoaderConfigTO -> {
            try {
                addSimpleTemplate.withNewCode(TemplateCodeNewTO.builder()
                        .code(templateLoaderConfigTO.getTemplateCode())
                        .description(templateLoaderConfigTO.getDescription())
                        .build());


                File file = new File(getClass().getClassLoader().getResource(templateLoaderConfigTO.getDocumentPath()).getFile());
                byte[] bytes = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream(templateLoaderConfigTO.getDocumentPath()));

                TemplateTO templateTO = addSimpleTemplate.forNew(TemplateNewTO.builder()
                        .code(templateLoaderConfigTO.getTemplateCode())
                        .taal(config.getLanguage())
                        .template(bytes)
//                        .template(FileUtils.readFileToByteArray(file))
                        .originalFilename(file.getName())
                        .build());

                activateSimpleOrCombinedTemplate.activateTemplate(templateTO.getId());
            } catch (Exception e) {
                log.error("Could not load WORD_PRACTICE_TEMPLATE", e);
            }
        });

        addDocument.metNieuweCode(BriefCodeNieuwTO.builder()
                .code(config.getDocumentCode())
                .description(config.getDescription())
                .build());

        DocumentTO documentTO = addDocument.forNew(DocumentNewTO.builder()
                .code(config.getDocumentCode())
                .taal(config.getLanguage())
                .templates(config.getTemplates().stream().map(TemplateLoaderConfigTO::getTemplateCode).collect(Collectors.toList()))
                .build());
        activateDocument.metId(documentTO.getId());

    }
}


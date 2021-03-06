package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefPreviewConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.DatasetProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePreview {

    private final GenerateSimpleTemplate generateSimpleTemplate;
    private final GenerateCombinedTemplate generateCombinedTemplate;
    private final GenerateDocument generateDocument;

    public CreatePreview(GenerateSimpleTemplate generateSimpleTemplate, GenerateCombinedTemplate generateCombinedTemplate, GenerateDocument generateDocument) {
        this.generateSimpleTemplate = generateSimpleTemplate;
        this.generateCombinedTemplate = generateCombinedTemplate;
        this.generateDocument = generateDocument;
    }

    public TemplateDataTO forTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return generateSimpleTemplate.voorTemplateMetId(templateId, briefConfigTO);

    }

    public TemplateDataTO forCombinedTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return generateCombinedTemplate.forCombinedTemplateWithId(templateId, briefConfigTO);
    }

    public TemplateDataTO forDocument(String documentId, DatasetProvider briefConfigTO) {

        return generateDocument.voorBriefMetId(documentId, briefConfigTO);
    }

    public TemplateDataTO forDocument(String documentId) {
        return forDocument(documentId, new BriefPreviewConfigTO());
    }

}

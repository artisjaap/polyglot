package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefPreviewConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.DatasetProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePreview {

    @Autowired
    private GenereerTemplate genereerTemplate;

    @Autowired
    private GenereerGecombineerdeTemplate genereerGecombineerdeTemplate;

    @Autowired
    private GenereerBrief genereerBrief;

    public TemplateDataTO forTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return genereerTemplate.voorTemplateMetId(templateId, briefConfigTO);

    }

    public TemplateDataTO forCombinedTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return genereerGecombineerdeTemplate.voorGecombineerdeTemplateMetId(templateId, briefConfigTO);
    }

    public TemplateDataTO forDocument(String documentId, DatasetProvider briefConfigTO) {

        return genereerBrief.voorBriefMetId(documentId, briefConfigTO);
    }

    public TemplateDataTO forDocument(String documentId){
        return forDocument(documentId, new BriefPreviewConfigTO());
    }

}

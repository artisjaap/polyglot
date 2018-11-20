package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefPreviewConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaakPreview {

    @Autowired
    private GenereerTemplate genereerTemplate;

    @Autowired
    private GenereerGecombineerdeTemplate genereerGecombineerdeTemplate;

    @Autowired
    private GenereerBrief genereerBrief;

    public TemplateDataTO vanTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return genereerTemplate.voorTemplateMetId(templateId, briefConfigTO);

    }

    public TemplateDataTO vanGecombineerdeTemplate(ObjectId templateId, BriefPreviewConfigTO briefConfigTO) {
        return genereerGecombineerdeTemplate.voorGecombineerdeTemplateMetId(templateId, briefConfigTO);
    }

    public TemplateDataTO vanBrief(ObjectId briefId, BriefPreviewConfigTO briefConfigTO) {

        return genereerBrief.voorBriefMetId(briefId, briefConfigTO);
    }

}

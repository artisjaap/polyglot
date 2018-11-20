package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActiveerTemplate;
import be.artisjaap.document.action.GecombineerdeTemplateToevoegen;
import be.artisjaap.document.action.to.GecombineerdeTemplateNieuwTO;
import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import be.aclvb.framework.common.plugin.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GecombineerdeTemplatePersister {

    @Autowired
    private GecombineerdeTemplateToevoegen gecombineerdeTemplateToevoegen;

    @Autowired
    private ActiveerTemplate activeerTemplate;

    public void maakGecombineerdeTemplate(String code, String ... templates) {
        GecombineerdeTemplateNieuwTO gecombineerdeTemplateNieuwTO = GecombineerdeTemplateNieuwTO.newBuilder().withCode(code).withTaal(Language.DUTCH).withTemplates(Arrays.asList(templates)).build();

        GecombineerdeTemplateTO gecombineerdeTemplateTO = gecombineerdeTemplateToevoegen.uit(gecombineerdeTemplateNieuwTO);
        activeerTemplate.activeerTemplate(gecombineerdeTemplateTO.getId());
    }
}

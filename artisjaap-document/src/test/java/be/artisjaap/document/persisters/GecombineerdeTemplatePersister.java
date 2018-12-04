package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActivateTemplate;
import be.artisjaap.document.action.AddCombinedTemplate;
import be.artisjaap.document.action.to.GecombineerdeTemplateNieuwTO;
import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GecombineerdeTemplatePersister {

    @Autowired
    private AddCombinedTemplate addCombinedTemplate;

    @Autowired
    private ActivateTemplate activateTemplate;

    public void maakGecombineerdeTemplate(String code, String ... templates) {
        GecombineerdeTemplateNieuwTO gecombineerdeTemplateNieuwTO = GecombineerdeTemplateNieuwTO.newBuilder().withCode(code).withTaal("DUTCH").withTemplates(Arrays.asList(templates)).build();

        GecombineerdeTemplateTO gecombineerdeTemplateTO = addCombinedTemplate.uit(gecombineerdeTemplateNieuwTO);
        activateTemplate.activeerTemplate(gecombineerdeTemplateTO.getId());
    }
}

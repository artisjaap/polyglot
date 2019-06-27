package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddCombinedTemplate;
import be.artisjaap.document.action.to.CombinedTemplateNewTO;
import be.artisjaap.document.action.to.CombinedTemplateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GecombineerdeTemplatePersister {

    @Autowired
    private AddCombinedTemplate addCombinedTemplate;

    @Autowired
    private ActivateSimpleOrCombinedTemplate activateSimpleTemplate;

    public void maakGecombineerdeTemplate(String code, String ... templates) {
        CombinedTemplateNewTO combinedTemplateNewTO = CombinedTemplateNewTO.newBuilder().withCode(code).withTaal("DUTCH").withTemplates(Arrays.asList(templates)).build();

        CombinedTemplateTO combinedTemplateTO = addCombinedTemplate.from(combinedTemplateNewTO);
        activateSimpleTemplate.activateTemplate(combinedTemplateTO.getId());
    }
}

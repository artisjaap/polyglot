package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.CombinedTemplateNewTO;
import be.artisjaap.document.model.CombinedTemplate;
import org.springframework.stereotype.Component;

@Component
public class CombinedTemplateNewAssembler implements Assembler<CombinedTemplate, CombinedTemplateNewTO> {


    @Override
    public CombinedTemplate assembleEntity(CombinedTemplateNewTO combinedTemplateNewTO) {
        return CombinedTemplate.newBuilder()
                .withNaam(combinedTemplateNewTO.code())
                .withTaal(combinedTemplateNewTO.taal())
                .withTemplates(combinedTemplateNewTO.templates())
                .build();
    }

    @Override
    public CombinedTemplateNewTO assembleTO(CombinedTemplate combinedTemplateAssembler) {
        return null;
    }
}

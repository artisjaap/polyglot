package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.CombinedTemplateTO;
import be.artisjaap.document.model.CombinedTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CombinedTemplateAssembler implements Assembler<CombinedTemplate, CombinedTemplateTO> {

    @Autowired
    private TemplateCodeAssembler templateCodeAssembler;

    @Override
    public CombinedTemplate assembleEntity(CombinedTemplateTO combinedTemplateTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public CombinedTemplateTO assembleTO(CombinedTemplate combinedTemplate) {
        return CombinedTemplateTO.newBuilder()
                .withCode(combinedTemplate.getCode())
                .withId(combinedTemplate.getId().toString())
                .withTaal(combinedTemplate.getTaal())
                .withTemplates(templateCodeAssembler.assembleTOFromCodes(combinedTemplate.getTemplates()))
                .withActief(combinedTemplate.getActief())
                .withAangemaaktOp(combinedTemplate.getTimeStamp())
                .build();
    }
}

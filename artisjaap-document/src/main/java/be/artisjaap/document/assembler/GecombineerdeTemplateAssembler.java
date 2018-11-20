package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import be.artisjaap.document.model.GecombineerdeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GecombineerdeTemplateAssembler implements Assembler<GecombineerdeTemplate, GecombineerdeTemplateTO> {

    @Autowired
    private TemplateCodeAssembler templateCodeAssembler;

    @Override
    public GecombineerdeTemplate assembleEntity(GecombineerdeTemplateTO gecombineerdeTemplateTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public GecombineerdeTemplateTO assembleTO(GecombineerdeTemplate gecombineerdeTemplate) {
        return GecombineerdeTemplateTO.newBuilder()
                .withCode(gecombineerdeTemplate.getCode())
                .withId(gecombineerdeTemplate.getId().toString())
                .withTaal(gecombineerdeTemplate.getTaal())
                .withTemplates(templateCodeAssembler.assembleTOFromCodes(gecombineerdeTemplate.getTemplates()))
                .withActief(gecombineerdeTemplate.getActief())
                .withAangemaaktOp(gecombineerdeTemplate.getTimeStamp())
                .build();
    }
}

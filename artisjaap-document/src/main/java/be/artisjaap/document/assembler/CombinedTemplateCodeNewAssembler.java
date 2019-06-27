package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import org.springframework.stereotype.Component;

@Component
public class CombinedTemplateCodeNewAssembler implements Assembler<GecombineerdeTemplateCode, TemplateCodeNewTO>{
    @Override
    public GecombineerdeTemplateCode assembleEntity(TemplateCodeNewTO templateCodeNewTO) {
        return GecombineerdeTemplateCode.newBuilder()
                .withCode(templateCodeNewTO.getCode())
                .withDescription(templateCodeNewTO.getDescription())
                .build();
    }

    @Override
    public TemplateCodeNewTO assembleTO(GecombineerdeTemplateCode gecombineerdeTemplateCode) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}

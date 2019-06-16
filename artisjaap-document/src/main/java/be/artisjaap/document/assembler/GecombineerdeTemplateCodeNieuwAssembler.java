package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateCodeNieuwTO;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import org.springframework.stereotype.Component;

@Component
public class GecombineerdeTemplateCodeNieuwAssembler implements Assembler<GecombineerdeTemplateCode, TemplateCodeNieuwTO>{
    @Override
    public GecombineerdeTemplateCode assembleEntity(TemplateCodeNieuwTO templateCodeNieuwTO) {
        return GecombineerdeTemplateCode.newBuilder()
                .withCode(templateCodeNieuwTO.getCode())
                .withDescription(templateCodeNieuwTO.getDescription())
                .build();
    }

    @Override
    public TemplateCodeNieuwTO assembleTO(GecombineerdeTemplateCode gecombineerdeTemplateCode) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}

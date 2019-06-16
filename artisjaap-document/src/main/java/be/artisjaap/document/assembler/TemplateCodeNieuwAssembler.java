package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateCodeNieuwTO;
import be.artisjaap.document.model.TemplateCode;
import org.springframework.stereotype.Component;

@Component
public class TemplateCodeNieuwAssembler implements Assembler<TemplateCode, TemplateCodeNieuwTO>{
    @Override
    public TemplateCode assembleEntity(TemplateCodeNieuwTO templateCodeNieuwTO) {
        return TemplateCode.newBuilder()
                .withCode(templateCodeNieuwTO.getCode())
                .withDescription(templateCodeNieuwTO.getDescription())
                .build();
    }

    @Override
    public TemplateCodeNieuwTO assembleTO(TemplateCode templateCode) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}

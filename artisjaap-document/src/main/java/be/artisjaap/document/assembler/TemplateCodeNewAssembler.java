package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.model.TemplateCode;
import org.springframework.stereotype.Component;

@Component
public class TemplateCodeNewAssembler implements Assembler<TemplateCode, TemplateCodeNewTO>{
    @Override
    public TemplateCode assembleEntity(TemplateCodeNewTO templateCodeNewTO) {
        return TemplateCode.newBuilder()
                .withCode(templateCodeNewTO.getCode())
                .withDescription(templateCodeNewTO.getDescription())
                .build();
    }

    @Override
    public TemplateCodeNewTO assembleTO(TemplateCode templateCode) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}

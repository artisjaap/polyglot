package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.BriefCodeTO;
import be.artisjaap.document.model.BriefCode;
import org.springframework.stereotype.Component;

@Component
public class DocumentCodeAssembler implements Assembler<BriefCode, BriefCodeTO>{
    @Override
    public BriefCode assembleEntity(BriefCodeTO briefCodeTO) {
        return null;
    }

    @Override
    public BriefCodeTO assembleTO(BriefCode briefCode) {
        return BriefCodeTO.builder()
                .code(briefCode.getCode())
                .description(briefCode.getDescription())
                .build();
    }
}

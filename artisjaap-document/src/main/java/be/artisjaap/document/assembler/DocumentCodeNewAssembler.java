package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.BriefCodeNieuwTO;
import be.artisjaap.document.model.BriefCode;
import org.springframework.stereotype.Component;

@Component
public class DocumentCodeNewAssembler implements Assembler<BriefCode, BriefCodeNieuwTO>{
    @Override
    public BriefCode assembleEntity(BriefCodeNieuwTO briefCodeNieuwTO) {
        return BriefCode.newBuilder()
                .withCode(briefCodeNieuwTO.getCode())
                .withDescription(briefCodeNieuwTO.getDescription())
                .build();
    }

    @Override
    public BriefCodeNieuwTO assembleTO(BriefCode briefCode) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}

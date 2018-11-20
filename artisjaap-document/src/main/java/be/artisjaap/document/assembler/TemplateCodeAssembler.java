package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateCodeTO;
import be.artisjaap.document.model.TemplateCode;
import be.artisjaap.document.model.mongo.TemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemplateCodeAssembler implements Assembler<TemplateCode, TemplateCodeTO> {
    @Autowired
    private TemplateCodeRepository templateCodeRepository;

    @Override
    public TemplateCode assembleEntity(TemplateCodeTO templateCodeTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public TemplateCodeTO assembleTO(TemplateCode templateCode) {
        return TemplateCodeTO.newBuilder()
                .withCode(templateCode.getCode())
                .withOmschrijvingNl(templateCode.getOmschrijvingNl())
                .withOmschrijvingFr(templateCode.getOmschrijvingFr())
                .build();
    }


    public List<TemplateCodeTO> assembleTOFromCodes(List<String> templateCodes) {
        return templateCodeRepository.findByCodeIn(templateCodes).stream().map(this::assembleTO).collect(Collectors.toList());
    }
}

package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.CombinedTemplateCodeTO;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import be.artisjaap.document.model.mongo.CombinedTemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CombinedTemplateCodeAssembler implements Assembler<GecombineerdeTemplateCode, CombinedTemplateCodeTO>{
    @Autowired
    private CombinedTemplateCodeRepository combinedTemplateCodeRepository;



    @Override
    public GecombineerdeTemplateCode assembleEntity(CombinedTemplateCodeTO combinedTemplateCodeTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public CombinedTemplateCodeTO assembleTO(GecombineerdeTemplateCode gecombineerdeTemplateCode) {
        return CombinedTemplateCodeTO.builder()
                .code(gecombineerdeTemplateCode.getCode())
                .description(gecombineerdeTemplateCode.getDescription())
                .build();
    }

    public List<CombinedTemplateCodeTO> assembleTOFromCodes(List<String> templateCodes) {
        return combinedTemplateCodeRepository.findByCodeIn(templateCodes).stream().map(this::assembleTO).collect(Collectors.toList());
    }
}

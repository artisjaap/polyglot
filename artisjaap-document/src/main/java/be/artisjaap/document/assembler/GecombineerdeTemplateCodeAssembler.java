package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.GecombineerdeTemplateCodeTO;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GecombineerdeTemplateCodeAssembler implements Assembler<GecombineerdeTemplateCode, GecombineerdeTemplateCodeTO>{
    @Autowired
    private GecombineerdeTemplateCodeRepository gecombineerdeTemplateCodeRepository;



    @Override
    public GecombineerdeTemplateCode assembleEntity(GecombineerdeTemplateCodeTO gecombineerdeTemplateCodeTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public GecombineerdeTemplateCodeTO assembleTO(GecombineerdeTemplateCode gecombineerdeTemplateCode) {
        return GecombineerdeTemplateCodeTO.newBuilder()
                .withCode(gecombineerdeTemplateCode.getCode())
                .withOmschrijvingNl(gecombineerdeTemplateCode.getOmschrijvingNl())
                .withOmschrijvingFr(gecombineerdeTemplateCode.getOmschrijvingFr())
                .build();
    }

    public List<GecombineerdeTemplateCodeTO> assembleTOFromCodes(List<String> templateCodes) {
        return gecombineerdeTemplateCodeRepository.findByCodeIn(templateCodes).stream().map(this::assembleTO).collect(Collectors.toList());
    }
}

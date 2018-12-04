package be.artisjaap.document.action;

import be.artisjaap.document.action.to.GecombineerdeTemplateCodeTO;
import be.artisjaap.document.action.to.GecombineerdeTemplateNieuwTO;
import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import be.artisjaap.document.action.to.TemplateCodeNieuwTO;
import be.artisjaap.document.assembler.GecombineerdeTemplateAssembler;
import be.artisjaap.document.assembler.GecombineerdeTemplateCodeAssembler;
import be.artisjaap.document.assembler.GecombineerdeTemplateCodeNieuwAssembler;
import be.artisjaap.document.assembler.GecombineerdeTemplateNieuwAssembler;
import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateCodeRepository;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddCombinedTemplate {
    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    @Autowired
    private GecombineerdeTemplateCodeRepository gecombineerdeTemplateCodeRepository;

    @Autowired
    private GecombineerdeTemplateNieuwAssembler gecombineerdeTemplateNieuwAssembler;

    @Autowired
    private GecombineerdeTemplateAssembler gecombineerdeTemplateAssembler;

    @Autowired
    private GecombineerdeTemplateCodeNieuwAssembler gecombineerdeTemplateCodeNieuwAssembler;

    @Autowired
    private GecombineerdeTemplateCodeAssembler gecombineerdeTemplateCodeAssembler;

    public GecombineerdeTemplateTO uit(GecombineerdeTemplateNieuwTO combinatie){
        GecombineerdeTemplate template = gecombineerdeTemplateNieuwAssembler.assembleEntity(combinatie);
        template = gecombineerdeTemplateRepository.save(template);
        return gecombineerdeTemplateAssembler.assembleTO(template);
    }

    public GecombineerdeTemplateCodeTO metNieuweCode(TemplateCodeNieuwTO templateCode){
        GecombineerdeTemplateCode gecombineerdeTemplateCode = gecombineerdeTemplateCodeRepository.save(gecombineerdeTemplateCodeNieuwAssembler.assembleEntity(templateCode));
       return gecombineerdeTemplateCodeAssembler.assembleTO(gecombineerdeTemplateCode);
    }
}

package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateCodeNieuwTO;
import be.artisjaap.document.action.to.TemplateCodeTO;
import be.artisjaap.document.action.to.TemplateNieuwTO;
import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.assembler.TemplateCodeAssembler;
import be.artisjaap.document.assembler.TemplateCodeNieuwAssembler;
import be.artisjaap.document.assembler.TemplateNieuwAssembler;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.TemplateCode;
import be.artisjaap.document.model.mongo.TemplateCodeRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EenvoudigeTemplateToevoegen {
    @Autowired
    private TemplateNieuwAssembler templateNieuwAssembler;

    @Autowired
    private TemplateAssembler templateAssembler;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateCodeRepository templateCodeRepository;

    @Autowired
    private TemplateCodeNieuwAssembler templateCodeNieuwAssembler;

    @Autowired
    private TemplateCodeAssembler templateCodeAssembler;

    public TemplateTO voor(TemplateNieuwTO templateTO){
        Template template = templateNieuwAssembler.assembleEntity(templateTO);
        template = templateRepository.save(template);
        return templateAssembler.assembleTO(template);
    }

    public TemplateCodeTO metNieuweCode(TemplateCodeNieuwTO templateCode){
        TemplateCode template = templateCodeRepository.save(templateCodeNieuwAssembler.assembleEntity(templateCode));
        return templateCodeAssembler.assembleTO(template);
    }
}

package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.action.to.TemplateCodeTO;
import be.artisjaap.document.action.to.TemplateNewTO;
import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.assembler.TemplateCodeAssembler;
import be.artisjaap.document.assembler.TemplateCodeNewAssembler;
import be.artisjaap.document.assembler.TemplateNewAssembler;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.TemplateCode;
import be.artisjaap.document.model.mongo.TemplateCodeRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSimpleTemplate {
    @Autowired
    private TemplateNewAssembler templateNewAssembler;

    @Autowired
    private TemplateAssembler templateAssembler;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateCodeRepository templateCodeRepository;

    @Autowired
    private TemplateCodeNewAssembler templateCodeNewAssembler;

    @Autowired
    private TemplateCodeAssembler templateCodeAssembler;

    public TemplateTO forNew(TemplateNewTO templateTO) {
        Template template = templateNewAssembler.assembleEntity(templateTO);
        template = templateRepository.save(template);
        return templateAssembler.assembleTO(template);
    }

    public TemplateCodeTO withNewCode(TemplateCodeNewTO templateCode) {
        TemplateCode template = templateCodeRepository.save(templateCodeNewAssembler.assembleEntity(templateCode));
        return templateCodeAssembler.assembleTO(template);
    }
}

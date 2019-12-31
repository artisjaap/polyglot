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
    private final TemplateNewAssembler templateNewAssembler;
    private final TemplateAssembler templateAssembler;
    private final TemplateRepository templateRepository;
    private final TemplateCodeRepository templateCodeRepository;
    private final TemplateCodeNewAssembler templateCodeNewAssembler;
    private final TemplateCodeAssembler templateCodeAssembler;

    public AddSimpleTemplate(TemplateNewAssembler templateNewAssembler, TemplateAssembler templateAssembler, TemplateRepository templateRepository, TemplateCodeRepository templateCodeRepository, TemplateCodeNewAssembler templateCodeNewAssembler, TemplateCodeAssembler templateCodeAssembler) {
        this.templateNewAssembler = templateNewAssembler;
        this.templateAssembler = templateAssembler;
        this.templateRepository = templateRepository;
        this.templateCodeRepository = templateCodeRepository;
        this.templateCodeNewAssembler = templateCodeNewAssembler;
        this.templateCodeAssembler = templateCodeAssembler;
    }

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

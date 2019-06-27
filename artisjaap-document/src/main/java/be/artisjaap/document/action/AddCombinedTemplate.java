package be.artisjaap.document.action;

import be.artisjaap.document.action.to.CombinedTemplateCodeTO;
import be.artisjaap.document.action.to.CombinedTemplateNewTO;
import be.artisjaap.document.action.to.CombinedTemplateTO;
import be.artisjaap.document.action.to.TemplateCodeNewTO;
import be.artisjaap.document.assembler.CombinedTemplateAssembler;
import be.artisjaap.document.assembler.CombinedTemplateCodeAssembler;
import be.artisjaap.document.assembler.CombinedTemplateCodeNewAssembler;
import be.artisjaap.document.assembler.CombinedTemplateNewAssembler;
import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.GecombineerdeTemplateCode;
import be.artisjaap.document.model.mongo.CombinedTemplateCodeRepository;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddCombinedTemplate {
    @Autowired
    private CombinedTemplateRepository combinedTemplateRepository;

    @Autowired
    private CombinedTemplateCodeRepository combinedTemplateCodeRepository;

    @Autowired
    private CombinedTemplateNewAssembler combinedTemplateNewAssembler;

    @Autowired
    private CombinedTemplateAssembler combinedTemplateAssembler;

    @Autowired
    private CombinedTemplateCodeNewAssembler combinedTemplateCodeNewAssembler;

    @Autowired
    private CombinedTemplateCodeAssembler combinedTemplateCodeAssembler;

    public CombinedTemplateTO from(CombinedTemplateNewTO combinatie) {
        CombinedTemplate template = combinedTemplateNewAssembler.assembleEntity(combinatie);
        template = combinedTemplateRepository.save(template);
        return combinedTemplateAssembler.assembleTO(template);
    }

    public CombinedTemplateCodeTO withNewCode(TemplateCodeNewTO templateCode) {
        GecombineerdeTemplateCode gecombineerdeTemplateCode = combinedTemplateCodeRepository.save(combinedTemplateCodeNewAssembler.assembleEntity(templateCode));
        return combinedTemplateCodeAssembler.assembleTO(gecombineerdeTemplateCode);
    }
}

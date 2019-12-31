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
import org.springframework.stereotype.Component;


@Component
public class AddCombinedTemplate {
    private final CombinedTemplateRepository combinedTemplateRepository;
    private final CombinedTemplateCodeRepository combinedTemplateCodeRepository;
    private final CombinedTemplateNewAssembler combinedTemplateNewAssembler;
    private final CombinedTemplateAssembler combinedTemplateAssembler;
    private final CombinedTemplateCodeNewAssembler combinedTemplateCodeNewAssembler;
    private final CombinedTemplateCodeAssembler combinedTemplateCodeAssembler;

    public AddCombinedTemplate(CombinedTemplateRepository combinedTemplateRepository, CombinedTemplateCodeRepository combinedTemplateCodeRepository, CombinedTemplateNewAssembler combinedTemplateNewAssembler, CombinedTemplateAssembler combinedTemplateAssembler, CombinedTemplateCodeNewAssembler combinedTemplateCodeNewAssembler, CombinedTemplateCodeAssembler combinedTemplateCodeAssembler) {
        this.combinedTemplateRepository = combinedTemplateRepository;
        this.combinedTemplateCodeRepository = combinedTemplateCodeRepository;
        this.combinedTemplateNewAssembler = combinedTemplateNewAssembler;
        this.combinedTemplateAssembler = combinedTemplateAssembler;
        this.combinedTemplateCodeNewAssembler = combinedTemplateCodeNewAssembler;
        this.combinedTemplateCodeAssembler = combinedTemplateCodeAssembler;
    }

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

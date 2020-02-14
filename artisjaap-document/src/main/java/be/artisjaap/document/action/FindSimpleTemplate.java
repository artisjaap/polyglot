package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class FindSimpleTemplate {
    private final TemplateRepository templateRepository;
    private final TemplateAssembler templateAssembler;

    public FindSimpleTemplate(TemplateRepository templateRepository, TemplateAssembler templateAssembler) {
        this.templateRepository = templateRepository;
        this.templateAssembler = templateAssembler;
    }

    public TemplateTO forTemplateId(String templateId) {
        return templateRepository.findById(new ObjectId(templateId)).map(templateAssembler::assembleTO).orElseThrow(() -> new IllegalStateException("template niet gevonden"));
    }
}

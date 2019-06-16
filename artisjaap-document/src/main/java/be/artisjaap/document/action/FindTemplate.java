package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindTemplate {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateAssembler templateAssembler;

    public TemplateTO forTemplateId(String templateId) {
        return templateRepository.findById(new ObjectId(templateId)).map(templateAssembler::assembleTO).orElseThrow(() -> new IllegalStateException("template niet gevonden"));
    }
}

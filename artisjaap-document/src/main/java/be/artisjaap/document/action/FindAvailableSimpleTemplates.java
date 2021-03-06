package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAvailableSimpleTemplates {

    private final TemplateAssembler templateAssembler;
    private final TemplateRepository templateRepository;

    public FindAvailableSimpleTemplates(TemplateAssembler templateAssembler, TemplateRepository templateRepository) {
        this.templateAssembler = templateAssembler;
        this.templateRepository = templateRepository;
    }

    public List<TemplateTO> withCodeAndLanguage(String code, String language) {
        return templateAssembler.assembleTO(templateRepository.findByCodeAndTaal(code, language));

    }

    public Optional<TemplateTO> activeWithCodeAndLanguage(String code, String language) {
        return templateRepository.findByCodeAndTaalAndActief(code, language).map(templateAssembler::assembleTO);

    }

    public List<TemplateTO> withCode(String code) {
        return templateAssembler.assembleTO(templateRepository.findByCode(code));

    }
}

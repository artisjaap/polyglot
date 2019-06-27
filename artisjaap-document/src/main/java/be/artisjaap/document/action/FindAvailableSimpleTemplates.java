package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAvailableSimpleTemplates {

    @Autowired
    private TemplateAssembler templateAssembler;

    @Autowired
    private TemplateRepository templateRepository;

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

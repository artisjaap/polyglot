package be.artisjaap.document.action;

import be.artisjaap.document.action.to.CombinedTemplateTO;
import be.artisjaap.document.assembler.CombinedTemplateAssembler;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAvailableCombinedTemplates {

    private final CombinedTemplateAssembler combinedTemplateAssembler;
    private final CombinedTemplateRepository combinedTemplateRepository;

    public FindAvailableCombinedTemplates(CombinedTemplateAssembler combinedTemplateAssembler, CombinedTemplateRepository combinedTemplateRepository) {
        this.combinedTemplateAssembler = combinedTemplateAssembler;
        this.combinedTemplateRepository = combinedTemplateRepository;
    }


    public List<CombinedTemplateTO> withCodeAndLanguage(String code, String language) {
        return combinedTemplateAssembler.assembleTO(combinedTemplateRepository.findByCodeAndTaal(code, language));

    }

    public Optional<CombinedTemplateTO> withCodeAndLanguageActive(String code, String language) {
        return combinedTemplateRepository.findByCodeAndTaalAndActief(code, language).map(combinedTemplateAssembler::assembleTO);
    }

    public List<CombinedTemplateTO> withCode(String code) {
        return combinedTemplateAssembler.assembleTO(combinedTemplateRepository.findByCode(code));
    }
}

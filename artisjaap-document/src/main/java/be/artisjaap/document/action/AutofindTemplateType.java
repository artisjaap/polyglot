package be.artisjaap.document.action;

import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.PaginaType;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AutofindTemplateType {

    private final TemplateRepository templateRepository;
    private final CombinedTemplateRepository combinedTemplateRepository;

    public AutofindTemplateType(TemplateRepository templateRepository, CombinedTemplateRepository combinedTemplateRepository) {
        this.templateRepository = templateRepository;
        this.combinedTemplateRepository = combinedTemplateRepository;
    }

    public PaginaType forPageCode(String code) {
        Optional<Template> template = templateRepository.findByCode(code).stream().findFirst();
        if (template.isPresent()) {
            return PaginaType.TEMPLATE;
        }

        List<CombinedTemplate> combinedTemplate = combinedTemplateRepository.findByCode(code);
        if (!combinedTemplate.isEmpty()) {
            return PaginaType.COMBINED;
        }

        return PaginaType.UNDEFINED;


    }
}

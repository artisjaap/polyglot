package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeactivateTemplate {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CombinedTemplateRepository combinedTemplateRepository;


    public void forSimpleTemplateWithCodeAndLanguage(String code, String language) {
        templateRepository.findByCodeAndTaalAndActief(code, language).ifPresent(template -> {
            template.deactivate();
            templateRepository.save(template);
        });

    }

    public void forCombinedTemplateWithCodeAndLanguage(String code, String language) {
        combinedTemplateRepository.findByCodeAndTaalAndActief(code, language).ifPresent(template -> {
            template.deactivate();
            combinedTemplateRepository.save(template);
        });
    }
}

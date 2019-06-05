package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeactivateTemplate {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;


    public void voorEenvoudigeTemplateMetCodeEnTaal(String code, String taal) {
        templateRepository.findByCodeAndTaalAndActief(code, taal).ifPresent(template -> {
            template.desactiveer();
            templateRepository.save(template);
        });

    }

    public void voorGecombineerdeTemplateMetCodeEnTaal(String code, String taal) {
        gecombineerdeTemplateRepository.findByCodeAndTaalAndActief(code, taal).ifPresent(template -> {
            template.desactiveer();
            gecombineerdeTemplateRepository.save(template);
        });
    }
}

package be.artisjaap.document.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCombinedTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCombinedTemplate.class);

    @Autowired
    private FindAvailableCombinedTemplates findAvailableCombinedTemplates;

    public boolean metCodeInTaal(String code, String taal) {
        boolean result = findAvailableCombinedTemplates.withCodeAndLanguageActive(code, taal).isPresent();
        if (!result) {
            logger.warn("geen actieve meervoudige template gevonden forTemplateId " + code + " in taal " + taal);
        }
        return result;
    }
}

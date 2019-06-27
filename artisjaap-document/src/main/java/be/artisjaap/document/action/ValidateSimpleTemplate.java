package be.artisjaap.document.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateSimpleTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ValidateSimpleTemplate.class);

    @Autowired
    private FindAvailableSimpleTemplates findAvailableSimpleTemplates;

    public boolean metCodeInTaal(String code, String taal) {
        boolean result = findAvailableSimpleTemplates.activeWithCodeAndLanguage(code, taal).isPresent();
        if (!result) {
            logger.warn("geen actieve enkelvoudige template gevonden forTemplateId " + code + " in taal " + taal);
        }
        return result;

    }

}

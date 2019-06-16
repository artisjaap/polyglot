package be.artisjaap.document.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValideerEnkelvoudigeTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ValideerEnkelvoudigeTemplate.class);

    @Autowired
    private ZoekBeschikbareEenvoudigeTemplates zoekBeschikbareEenvoudigeTemplates;

    public boolean metCodeInTaal(String code, String taal) {
        boolean result = zoekBeschikbareEenvoudigeTemplates.metCodeInTaalActief(code, taal).isPresent();
        if (!result) {
            logger.warn("geen actieve enkelvoudige template gevonden forTemplateId " + code + " in taal " + taal);
        }
        return result;

    }

}

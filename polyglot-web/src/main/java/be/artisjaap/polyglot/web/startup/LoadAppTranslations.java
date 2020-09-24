package be.artisjaap.polyglot.web.startup;

import be.artisjaap.i18n.action.I18nCreateTranslation;
import be.artisjaap.i18n.action.to.NewTranslationTO;
import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class LoadAppTranslations extends AbstractInitScript {

    @Autowired
    private I18nCreateTranslation i18nCreateTranslation;
    
    @Override
    public String omschrijving() {
        return "Load front end translations";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public Integer cardinality() {
        return 0;
    }

    @Override
    public void execute() {
        translationsNl();
        translationsEn();

    }

    private void translationsEn() {
        Properties translations = new Properties();
        translations.put("MAIN_LOGIN", "login");
        translations.put("MAIN_LOGOUT", "logout");
        translations.put("MAIN_HOME", "home");
        translations.put("MAIN_STUDENTS", "students");
        loadTranslations(translations, "en", "polyglot-front");
    }

    private void translationsNl() {
        Properties translations = new Properties();
        translations.put("MAIN_LOGIN", "aanmelden");
        translations.put("MAIN_LOGOUT", "afmelden");
        translations.put("MAIN_HOME", "start");
        translations.put("MAIN_STUDENTS", "studenten");
        loadTranslations(translations, "nl", "polyglot-front");
    }

    private void loadTranslations(Properties properties, String isoCode, String bundle){
        properties.entrySet().forEach(entry -> {
            i18nCreateTranslation.forTranslation(NewTranslationTO.builder()
                    .bundleName(bundle)
                    .languageIsoCode(isoCode)
                    .key(entry.getKey().toString())
                    .translation(entry.getValue().toString())
                    .build());
        });
    }
}

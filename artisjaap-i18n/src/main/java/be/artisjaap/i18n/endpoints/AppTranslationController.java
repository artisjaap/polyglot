package be.artisjaap.i18n.endpoints;

import be.artisjaap.i18n.action.I18nTranslationBundle;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/api")
public class AppTranslationController {

    private final I18nTranslationBundle i18nTranslationBundle;

    public AppTranslationController(I18nTranslationBundle i18nTranslationBundle) {
        this.i18nTranslationBundle = i18nTranslationBundle;
    }

    @RequestMapping(value = "/app-translations/{lang}/{bundle}", method = RequestMethod.GET)
    public Properties allTranslationsForBundleInLanguage(@PathVariable String lang, @PathVariable String bundle) {
        Properties p = new Properties();
        i18nTranslationBundle.findAllForLanguageAndBundle(lang, bundle)
                .forEach(translation -> p.put(translation.key(), translation.translation()));
        return p;
    }
}

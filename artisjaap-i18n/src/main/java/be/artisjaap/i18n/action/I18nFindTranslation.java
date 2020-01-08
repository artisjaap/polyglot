package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.TranslationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class I18nFindTranslation {
    private final I18nTranslationCache i18nTranslationCache;

    public I18nFindTranslation(I18nTranslationCache i18nTranslationCache) {
        this.i18nTranslationCache = i18nTranslationCache;
    }

    public Optional<String> translationForKey(String key, String languageIsoCode){
        return i18nTranslationCache.translationForKey(key, languageIsoCode);
    }

    public List<TranslationTO> findAllTranslations(){
        return i18nTranslationCache.allTranslations();
    }
}

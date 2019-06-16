package be.artisjaap.i18n.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindTranslation {
    @Autowired
    private TranslationCache translationCache;

    public Optional<String> translationForKey(String key, String languageIsoCode){
        return translationCache.translationForKey(key, languageIsoCode);
    }
}

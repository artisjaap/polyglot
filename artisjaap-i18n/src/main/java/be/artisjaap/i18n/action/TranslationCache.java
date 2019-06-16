package be.artisjaap.i18n.action;

import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.TranslationRepository;
import com.sun.java.accessibility.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TranslationCache {

    private boolean initialized = false;

    private Map<String, Map<String, Translation>> translationsPerLanguage = new HashMap<>();

    @Autowired
    private TranslationRepository translationRepository;


    Optional<String> translationForKey(String key, String languageIsoCode) {
        if(!initialized){
            reloadAllFromDB();
            initialized = true;
        }
        return Optional.ofNullable(allTranslationsForLanguage(languageIsoCode).get(key)).map(Translation::getTranslation);
    }

    Map<String, Translation> allTranslationsForLanguage(String languageIsoCode) {
        return translationsPerLanguage.getOrDefault(languageIsoCode, new HashMap<>());
    }


    void reloadAllFromDB(){
        translationsPerLanguage = translationRepository.findAll().stream().collect(Collectors.groupingBy(Translation::getLanguageIsoCode, Collectors.toMap(Translation::getKey, Function.identity())));
    }
}

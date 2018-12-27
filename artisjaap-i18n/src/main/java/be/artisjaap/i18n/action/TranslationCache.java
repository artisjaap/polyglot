package be.artisjaap.i18n.action;

import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TranslationCache {

    private Map<String, Map<String, Translation>> translationsPerLanguage = new HashMap<>();

    @Autowired
    private TranslationRepository translationRepository;


    Optional<String> translationForKey(String key, String languageIsoCode) {
        return Optional.ofNullable(allTranslationsForLanguage(languageIsoCode).get(key)).map(Translation::getTranslation);
    }

    Map<String, Translation> allTranslationsForLanguage(String languageIsoCode) {
        return translationsPerLanguage.getOrDefault(languageIsoCode, new HashMap<>());
    }


    void reloadAllFromDB(){
        translationRepository.findAll().stream().collect(Collectors.groupingBy(Translation::getLanguageIsoCode
                                                        , Collectors.collectingAndThen(Collectors.groupingBy(Translation::getKey, Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), l -> l.stream().findFirst().get())))));

    }
}

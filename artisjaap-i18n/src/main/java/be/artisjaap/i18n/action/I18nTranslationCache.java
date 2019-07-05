package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.assembler.I18nTranslationAssembler;
import be.artisjaap.i18n.model.mongo.I18nTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class I18nTranslationCache {

    private boolean initialized = false;

    private Map<String, Map<String, TranslationTO>> translationsPerLanguage = new HashMap<>();

    @Autowired
    private I18nTranslationRepository i18nTranslationRepository;

    @Autowired
    private I18nTranslationAssembler i18nTranslationAssembler;

    List<TranslationTO> allTranslations() {
        return translationsPerLanguage.entrySet().stream().flatMap(entity -> entity.getValue().entrySet().stream())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    Optional<String> translationForKey(String key, String languageIsoCode) {
        if (!initialized) {
            reloadAllFromDB();
            initialized = true;
        }
        return Optional.ofNullable(allTranslationsForLanguage(languageIsoCode).get(key)).map(TranslationTO::translation);
    }

    Map<String, TranslationTO> allTranslationsForLanguage(String languageIsoCode) {
        return translationsPerLanguage.getOrDefault(languageIsoCode, new HashMap<>());
    }


    void reloadAllFromDB() {
        translationsPerLanguage = i18nTranslationRepository.findAll().stream()
                .map(i18nTranslationAssembler::assembleTO)
                .collect(Collectors.groupingBy(TranslationTO::languageIsoCode, Collectors.toMap(TranslationTO::key, Function.identity())));
    }
}

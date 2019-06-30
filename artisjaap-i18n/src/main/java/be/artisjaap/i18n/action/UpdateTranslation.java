package be.artisjaap.i18n.action;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateTranslation {
    @Autowired
    private TranslationRepository translationRepository;

    public TranslationTO byKey(String key, String language, String translation){
        Translation translation = translationRepository.findByKeyAndLanguageIsoCode(key, language).orElseThrow(() -> new ValidationException("Cant find translation"));
        translation.setTranslation(translation);
        translationRepository.save(translation);

    }
}

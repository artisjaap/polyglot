package be.artisjaap.i18n.action;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.assembler.I18nTranslationAssembler;
import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class I18nUpdateTranslation {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private I18nTranslationAssembler i18nTranslationAssembler;

    public TranslationTO byKey(String key, String language, String translation) {
        Translation trans = translationRepository.findByKeyAndLanguageIsoCode(key, language).orElseThrow(() -> new ValidationException("Cant find translation"));
        trans.setTranslation(translation);
        translationRepository.save(trans);

        return i18nTranslationAssembler.assembleTO(trans);

    }
}

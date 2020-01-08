package be.artisjaap.i18n.action;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.assembler.I18nTranslationAssembler;
import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.I18nTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class I18nUpdateTranslation {
    private final I18nTranslationRepository i18nTranslationRepository;

    private final I18nTranslationAssembler i18nTranslationAssembler;

    public I18nUpdateTranslation(I18nTranslationRepository i18nTranslationRepository, I18nTranslationAssembler i18nTranslationAssembler) {
        this.i18nTranslationRepository = i18nTranslationRepository;
        this.i18nTranslationAssembler = i18nTranslationAssembler;
    }

    public TranslationTO byKey(String key, String language, String translation) {
        Translation trans = i18nTranslationRepository.findByKeyAndLanguageIsoCode(key, language).orElseThrow(() -> new ValidationException("Cant find translation"));
        trans.setTranslation(translation);
        i18nTranslationRepository.save(trans);

        return i18nTranslationAssembler.assembleTO(trans);

    }
}

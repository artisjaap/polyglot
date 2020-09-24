package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.assembler.I18nTranslationAssembler;
import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.I18nTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class I18nTranslationBundle {
    private final I18nTranslationCache i18nTranslationCache;

    private final I18nTranslationRepository i18nTranslationRepository;

    private final I18nTranslationAssembler i18nTranslationAssembler;

    public I18nTranslationBundle(I18nTranslationCache i18nTranslationCache, I18nTranslationRepository i18nTranslationRepository, I18nTranslationAssembler i18nTranslationAssembler) {
        this.i18nTranslationCache = i18nTranslationCache;
        this.i18nTranslationRepository = i18nTranslationRepository;
        this.i18nTranslationAssembler = i18nTranslationAssembler;
    }

    public List<TranslationTO> findAllForBundle(String bundleName){
        List<Translation> allByBundleName = i18nTranslationRepository.findAllByBundleName(bundleName);
        return i18nTranslationAssembler.assembleTOs(allByBundleName);
    }

    public List<TranslationTO> findAllForLanguageAndBundle(String language, String bundleName){
        List<Translation> allByBundleName = i18nTranslationRepository.findAllByLanguageIsoCodeAndBundleName(language, bundleName);
        return i18nTranslationAssembler.assembleTOs(allByBundleName);
    }

    

}


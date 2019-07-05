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
    @Autowired
    private I18nTranslationCache i18nTranslationCache;

    @Autowired
    private I18nTranslationRepository i18nTranslationRepository;

    @Autowired
    private I18nTranslationAssembler i18nTranslationAssembler;

    public List<TranslationTO> findAllForBundle(String bundleName){
        List<Translation> allByBundleName = i18nTranslationRepository.findAllByBundleName(bundleName);
        return i18nTranslationAssembler.assembleTOs(allByBundleName);
    }

}


package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.assembler.NewTranslationAssembler;
import be.artisjaap.i18n.assembler.TranslationAssembler;
import be.artisjaap.i18n.model.Translation;
import be.artisjaap.i18n.model.mongo.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TranslationBundle {
    @Autowired
    private TranslationCache translationCache;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;

    public List<TranslationTO> findAllForBundle(String bundleName){
        List<Translation> allByBundleName = translationRepository.findAllByBundleName(bundleName);
        return translationAssembler.assembleTOs(allByBundleName);
    }

}


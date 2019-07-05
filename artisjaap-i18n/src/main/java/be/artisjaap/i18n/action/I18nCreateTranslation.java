package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.NewTranslationTO;
import be.artisjaap.i18n.assembler.I18nNewTranslationAssembler;
import be.artisjaap.i18n.model.mongo.I18nTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class I18nCreateTranslation {
    @Autowired
    private I18nTranslationRepository i18nTranslationRepository;

    @Autowired
    private I18nNewTranslationAssembler i18nNewTranslationAssembler;

    public void forTranslation(NewTranslationTO translationTO){
        i18nTranslationRepository.save(i18nNewTranslationAssembler.assembleEntity(translationTO));
    }
}

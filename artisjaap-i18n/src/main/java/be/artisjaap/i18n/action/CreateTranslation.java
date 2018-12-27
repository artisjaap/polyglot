package be.artisjaap.i18n.action;

import be.artisjaap.i18n.action.to.NewTranslationTO;
import be.artisjaap.i18n.assembler.NewTranslationAssembler;
import be.artisjaap.i18n.model.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTranslation {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private NewTranslationAssembler newTranslationAssembler;

    public void forTranslation(NewTranslationTO translationTO){
        translationRepository.save(newTranslationAssembler.assembleEntity(translationTO));
    }
}

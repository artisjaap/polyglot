package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.UpdateTranslationTO;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTranslation {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;

    public TranslationTO forTranslation(UpdateTranslationTO translationTO){
        Translation translation = translationRepository.findByIdOrThrow(new ObjectId(translationTO.translationId()));
        translation.setLanguageA(translationTO.languageFrom());
        translation.setLanguageB(translationTO.languageTo());
        translationRepository.save(translation);
        return translationAssembler.assembleTO(translation);
    }
}

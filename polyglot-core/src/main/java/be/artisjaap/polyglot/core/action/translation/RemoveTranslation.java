package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.lesson.FindLessonHeader;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveTranslation {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private FindTranslations findTranslations;

    @Autowired
    private FindLessonHeader findLessonHeader;

    public TranslationTO withId(String translationId){
        TranslationTO translationTO = findTranslations.byId(translationId).orElseThrow(() -> new ValidationException("Translation not found"));
        if(!findLessonHeader.containingTranslation(translationId).isEmpty()){
            throw new ValidationException("Translation used in lesson cannot be deleted");
        }
        translationRepository.deleteById(new ObjectId(translationId));

        return translationTO;
    }
}

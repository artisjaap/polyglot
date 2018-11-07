package be.artisjaap.polyglot.core.action;

import be.artisjaap.core.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationPracticeTO;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusTranslation {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private TranslationPracticeAssembler translationPracticeAssembler;

    public TranslationPracticeTO toStatus(String translationId, ProgressStatus status){
        TranslationPractice translation = translationPracticeRepository.findByTranslationIdOrThrow(MongoUtils.toObjectId(translationId));
        return translationPracticeAssembler.assembleTO(updateAndSaveIfDifferent(translation, status));
    }

    private TranslationPractice updateAndSaveIfDifferent(TranslationPractice translationPractice, ProgressStatus newStatus){
        if(translationPractice.getProgressStatus() != newStatus){
            translationPractice.setProgressStatus(newStatus );
            translationPracticeRepository.save(translationPractice);
        }
        return translationPractice;
    }
}

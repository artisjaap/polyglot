package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationPracticeTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateStatusTranslation {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private TranslationPracticeAssembler translationPracticeAssembler;

    @Autowired
    private FindTranslationsInPractice findTranslationsInPractice;


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

    public void allInStatusTo(String userId, String languagePairId, ProgressStatus from, ProgressStatus to){
        List<TranslationTO> translationTOS = findTranslationsInPractice.inStatus(userId, languagePairId, from);
        translationTOS.stream().forEach(translation -> toStatus(translation.id(), to));
    }
}

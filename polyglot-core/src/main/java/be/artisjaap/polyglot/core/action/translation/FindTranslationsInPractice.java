package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FindTranslationsInPractice {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;



    public List<TranslationTO> inStatus(String userId, String languagePairId, ProgressStatus status){
        List<TranslationPractice> practiceTranslations = translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatusIn(new ObjectId(userId), new ObjectId(languagePairId), Collections.singletonList(status));
        Set<ObjectId> translationIds = practiceTranslations.stream().map(TranslationPractice::getTranslationId).collect(Collectors.toSet());
        return StreamSupport.stream(translationRepository.findAllById(translationIds).spliterator(), false)
                .map(translationAssembler::assembleTO)
                .collect(Collectors.toList());


    }
}

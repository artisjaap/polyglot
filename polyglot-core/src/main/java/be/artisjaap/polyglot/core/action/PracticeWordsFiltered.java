package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.PagedTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import be.artisjaap.polyglot.core.model.MergeTranslationPractice;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PracticeWordsFiltered {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;

    @Autowired
    private MergeTranslationPractice mergeTranslationPractice;

    public PagedTO<PracticeWordTO> withFilter(TranslationFilterTO translationFilterTO){
        Page<Translation> translationsForFilter = translationRepository.findTranslationsForFilter(translationFilterTO);

        List<PracticeWordTO> practiceWordTOS = mergeTranslationPractice.mergeForTranslations(translationsForFilter.stream().map(Translation::getId).map(ObjectId::toString).collect(Collectors.toList()));

        return PagedTO.from(translationsForFilter, practiceWordTOS);
    }
}

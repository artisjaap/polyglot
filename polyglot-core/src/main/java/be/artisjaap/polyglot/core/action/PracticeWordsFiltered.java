package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.PagedTO;
import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PracticeWordsFiltered {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;

    public PagedTO<TranslationTO> withFilter(TranslationFilterTO translationFilterTO){
        Page<Translation> translationsForFilter = translationRepository.findTranslationsForFilter(translationFilterTO);
        return PagedTO.from(translationsForFilter, translationAssembler);
    }
}

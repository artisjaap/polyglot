package be.artisjaap.polyglot.core.action.translation;

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
public class TranslationsFiltered {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;

    public PagedTO<TranslationTO> withFilter(TranslationFilterTO filterTO){
        Page<Translation> translationsForFilter = translationRepository.findTranslationsForFilter(filterTO);

        return PagedTO.from(translationsForFilter, translationAssembler);
    }
}

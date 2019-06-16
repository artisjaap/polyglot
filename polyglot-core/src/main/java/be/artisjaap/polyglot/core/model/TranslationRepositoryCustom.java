package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import org.springframework.data.domain.Page;

public interface TranslationRepositoryCustom {

    Page<Translation> findTranslationsForFilter(TranslationFilterTO filter);

}

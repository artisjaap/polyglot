package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TranslationRepositoryCustom {

    Page<Translation> findTranslationsForFilter(TranslationFilterTO filter);

    List<Translation> findLatestForLanguagePair(ObjectId languagePairId, Integer count);

}

package be.artisjaap.polyglot.core.action.documebts;

import be.artisjaap.polyglot.core.action.documebts.docconfig.AbstractDocumentConfig;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;

import java.util.List;

public interface AllDatasets {

    AbstractDocumentConfig voegLanguagePairDatasetToeVoor(LanguagePairTO languagePairTO);

    AbstractDocumentConfig translationDataSetFrom(List<TranslationJournalTO> translationJournals);
}

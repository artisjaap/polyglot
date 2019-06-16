package be.artisjaap.polyglot.core.action.documebts;

import be.artisjaap.polyglot.core.action.documebts.docconfig.AbstractDocumentConfig;
import be.artisjaap.polyglot.core.action.to.JournalStatisticsTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.action.to.UserTO;

import java.util.List;

public interface AllDatasets {

    AbstractDocumentConfig languagePairDataSetFrom(LanguagePairTO languagePairTO);

    AbstractDocumentConfig translationJournalDataSetFrom(List<TranslationJournalTO> translationJournals);

    AbstractDocumentConfig userDataSetFrom(UserTO userTO);

    AbstractDocumentConfig journalStatisticsDataSetFrom(JournalStatisticsTO journalStatistics);

}

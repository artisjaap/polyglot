package be.artisjaap.polyglot.core.action.documents.docconfig;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.polyglot.core.action.documents.AllDatasets;
import be.artisjaap.polyglot.core.action.documents.DatasetProviderFactory;
import be.artisjaap.polyglot.core.action.to.JournalStatisticsTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.UserTO;

import java.util.List;

public abstract class AbstractDocumentConfig implements AllDatasets, DocumentDefinition  {


    protected DatasetProviderFactory datasetProviderFactory = new DatasetProviderFactory();


    @Override
    public AbstractDocumentConfig languagePairDataSetFrom(LanguagePairTO languagePairTO) {
        datasetProviderFactory.languagePairDataSetFrom(languagePairTO);
        return this;
    }

    @Override
    public AbstractDocumentConfig translationJournalDataSetFrom(List<TranslationJournalTO> translationJournals) {
        datasetProviderFactory.translationDataSetFrom(translationJournals);
        return this;
    }

    @Override
    public AbstractDocumentConfig userDataSetFrom(UserTO userTO) {
        datasetProviderFactory.userDataSetFrom(userTO);
        return this;
    }

    @Override
    public AbstractDocumentConfig journalStatisticsDataSetFrom(JournalStatisticsTO journalStatistics) {
        datasetProviderFactory.journalStatisticsDataSetFrom(journalStatistics);
        return this;
    }

    public AbstractDocumentConfig translationsDataSetFrom(List<TranslationTO> translations) {
        datasetProviderFactory.translationsFrom(translations);
        return this;
    }

    @Override
    public BriefConfigTO documentConfigForPreview(String language) {
        return BriefConfigTO.builder()
                .datasetProvider(documentConfigForPreview())
                .code(documentCode().name())
                .taal(language)
                .build();
    }

    @Override
    public BriefConfigTO.BriefConfigTOBuilder buildConfig() {
        return  BriefConfigTO.builder().datasetProvider(datasetProviderFactory);
    }

    abstract AbstractDocumentConfig createInstance();

}

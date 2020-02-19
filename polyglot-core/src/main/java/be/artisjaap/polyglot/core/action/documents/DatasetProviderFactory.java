package be.artisjaap.polyglot.core.action.documents;

import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetMeta;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.model.datasets.*;

import java.util.List;

public class DatasetProviderFactory implements DatasetProvider {

    private final DatasetProviderImpl datasetProvider = new DatasetProviderImpl();

    public static DatasetProviderFactory create(){
        return new DatasetProviderFactory();
    }

    @Override
    public DatasetConfig get(String dataset) {
        return datasetProvider.get(dataset);
    }

    @Override
    public List<String> datasetNames() {
        return datasetProvider.datasetNames();
    }

    @Override
    public List<DatasetMeta> datasetsMeta() {
        return datasetProvider.datasetsMeta();
    }



    public DatasetProviderFactory languagePairDataSetFrom(LanguagePairTO languagePairTO) {
        datasetProvider.add("languagePair", LanguagePairDataSet.from(languagePairTO));
        return this;
    }


    public DatasetProviderFactory translationDataSetFrom(List<TranslationJournalTO> translationJournals){
        datasetProvider.add("result", TranslationResultDataSet.from(translationJournals), TranslationResultDataSet.class) ;
        return this;
    }

    public DatasetProviderFactory journalStatisticsDataSetFrom(JournalStatisticsTO journalStatisticsTO){
        datasetProvider.add("journalstats", JournalStatisticsDataSet.from(journalStatisticsTO));
        return this;
    }

    public DatasetProviderFactory journalTranslations(List<TranslationJournalTO> translationJournalList) {
        datasetProvider.add("journalTranslations", JournalTranslationsDataSet.from(translationJournalList), JournalTranslationsDataSet.class);
        return this;
    }

    public DatasetProviderFactory userDataSetFrom(UserTO userTO){
        datasetProvider.add("user", UserDataSet.from(userTO));
        return this;
    }


    public DatasetProviderFactory translationsFrom(List<TranslationTO> translations){
        datasetProvider.add("translations", TranslationDataSet.from(translations), TranslationDataSet.class);
        return this;
    }

    public DatasetProviderFactory addDummyLanguagePairDataSet(){
        datasetProvider.add("languagePair", LanguagePairDataSet.dummy());
        return this;
    }

    public DatasetProviderFactory addDummyTranslationResultDataSet(){
        datasetProvider.add("result", TranslationResultDataSet.dummyList(10), TranslationResultDataSet.class);
        return this;
    }

    public DatasetProviderFactory addDummyJournalTranslationDataSet(List<TranslationJournalTO> translationJournalList) {
        datasetProvider.add("journalTranslations", JournalTranslationsDataSet.dummyList(10), JournalTranslationsDataSet.class);
        return this;
    }

    public DatasetProviderFactory addDummyUserDataSet(){
        datasetProvider.add("user", UserDataSet.dummy());
        return this;
    }

    public DatasetProviderFactory addDummyJournalStatisticsDataSet(){
        datasetProvider.add("journalstats", JournalStatisticsDataSet.dummy());
        return this;
    }

    public DatasetProviderFactory addDummyTranslationDataSet(){
        datasetProvider.add("translations", TranslationDataSet.dummyList(10), TranslationDataSet.class);
        return this;
    }

    public DatasetProvider addAllDummy() {
        addDummyLanguagePairDataSet();
        addDummyTranslationResultDataSet();
        addDummyUserDataSet();
        addDummyJournalStatisticsDataSet();
        addDummyTranslationDataSet();
        return this;
    }


}

package be.artisjaap.polyglot.core.action.documebts;

import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetMeta;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.model.datasets.LanguagePairDataSet;
import be.artisjaap.polyglot.core.model.datasets.TranslationResultDataSet;

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

    public DatasetProviderFactory voegLanguagePairDatasetToeVoor(LanguagePairTO languagePairTO) {
        datasetProvider.add("languagePair", LanguagePairDataSet.from(languagePairTO));
        return this;
    }


    public DatasetProviderFactory translationDataSetFrom(List<TranslationJournalTO> translationJournals){
        datasetProvider.add("result", TranslationResultDataSet.from(translationJournals), TranslationResultDataSet.class) ;
        return this;
    }

    public DatasetProviderFactory addDummyLanguagePairDatast(){
        datasetProvider.add("languagePair", LanguagePairDataSet.dummy());
        return this;
    }

    public DatasetProviderFactory addDummyTranslationDataset(){
        datasetProvider.add("result", TranslationResultDataSet.dummyList(10), TranslationResultDataSet.class);
        return this;
    }
}

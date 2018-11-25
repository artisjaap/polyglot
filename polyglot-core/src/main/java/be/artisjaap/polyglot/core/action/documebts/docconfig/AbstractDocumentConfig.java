package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.polyglot.core.action.documebts.AllDatasets;
import be.artisjaap.polyglot.core.action.documebts.DatasetProviderFactory;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;

import java.util.List;

public abstract class AbstractDocumentConfig implements AllDatasets, DocumentDefinition  {


    protected DatasetProviderFactory datasetProviderFactory = new DatasetProviderFactory();


    @Override
    public AbstractDocumentConfig voegLanguagePairDatasetToeVoor(LanguagePairTO languagePairTO) {
        datasetProviderFactory.voegLanguagePairDatasetToeVoor(languagePairTO);
        return this;
    }

    @Override
    public AbstractDocumentConfig translationDataSetFrom(List<TranslationJournalTO> translationJournals) {
        datasetProviderFactory.translationDataSetFrom(translationJournals);
        return this;
    }

    @Override
    public BriefConfigTO documentConfigForPreview(String language) {
        return BriefConfigTO.newBuilder()
                .withDatasetProvider(documentConfigForPreview())
                .withCode(documentCode().name())
                .withTaal(language)
                .build();
    }

    @Override
    public BriefConfigTO.Builder buildConfig() {
        return  BriefConfigTO.newBuilder().withDatasetProvider(datasetProviderFactory);
    }


    protected AbstractDocumentConfig addDummyLanguagePairDataset(){
        datasetProviderFactory.addDummyLanguagePairDatast();
        return this;
    }

    protected AbstractDocumentConfig addDummyTranslationDataset(){
        datasetProviderFactory.addDummyTranslationDataset();
        return this;
    }

    abstract AbstractDocumentConfig createInstance();

}

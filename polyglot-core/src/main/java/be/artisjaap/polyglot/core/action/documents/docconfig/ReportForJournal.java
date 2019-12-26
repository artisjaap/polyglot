package be.artisjaap.polyglot.core.action.documents.docconfig;

import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.polyglot.core.action.documents.DatasetProviderFactory;
import org.springframework.stereotype.Component;

@Component
public class ReportForJournal extends AbstractDocumentConfig {


    @Override
    public DocumentCode documentCode() {
        return DocumentCode.REPORT_FOR_JOURNAL;
    }


    @Override
    public DatasetProvider documentConfigForPreview() {
        return new DatasetProviderFactory()
                .addDummyLanguagePairDataSet()
                .addDummyTranslationResultDataSet()
                .addDummyUserDataSet()
                .addDummyJournalStatisticsDataSet();

    }

    @Override
    AbstractDocumentConfig createInstance() {
        return new ReportForJournal();
    }
}

package be.artisjaap.polyglot.core.action;

import be.artisjaap.document.action.GenereerBrief;
import be.artisjaap.document.action.GenereerFieldsXml;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.model.datasets.LanguagePairDataSet;
import be.artisjaap.polyglot.core.model.datasets.TranslationResultDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenerateReportForJournal {
    @Autowired
    private GenereerBrief genereerBrief;

    @Autowired
    private GenereerFieldsXml genereerFieldsXml;

    @Autowired
    private CreateJournalDocument createJournalDocument;

    @Autowired
    private FindLanguagePair findLanguagePair;

    public Optional<byte[]> withData(LanguagePracticeJournalTO result){
        //TODO upload word template as action
        //createJournalDocument.createOfNotExists();

        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .withTaal("DUTCH")
                .withCode("JOURNAL_REPORT")
                .withDatasetProvider(DatasetProviderImpl.create()
                        .add("languagePair", languagePairDataSetFrom(result))
                        .add("result", translationDataSetFrom(result), TranslationResultDataSet.class))

                .build();

        //TODO seperate action for fields.xml
        //genereerFieldsXml.voorBrief(briefConfigTO);

        return genereerBrief.voor(briefConfigTO);
    }

    private LanguagePairDataSet languagePairDataSetFrom(LanguagePracticeJournalTO result){
        LanguagePairTO languagePairTO = findLanguagePair.byId(result.languagePairId());
        return LanguagePairDataSet.from(languagePairTO) ;
    }

    private List<TranslationResultDataSet> translationDataSetFrom(LanguagePracticeJournalTO result){
        return TranslationResultDataSet.from(result.translationJournalList()) ;
    }

}
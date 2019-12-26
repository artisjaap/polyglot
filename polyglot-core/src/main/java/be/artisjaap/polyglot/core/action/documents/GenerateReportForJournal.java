package be.artisjaap.polyglot.core.action.documents;

import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.JournalStatisticsAggregator;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentCode;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentForCodeFactory;
import be.artisjaap.polyglot.core.action.journal.CreateJournalDocument;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.user.FindUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class GenerateReportForJournal {
    @Resource
    private GenerateDocument generateDocument;

    @Resource
    private CreateJournalDocument createJournalDocument;

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private DocumentForCodeFactory documentForCodeFactory;

    @Resource
    private FindUser findUser;

    @Resource
    private JournalStatisticsAggregator journalStatisticsAggregator;

    public Optional<byte[]> withData(LanguagePracticeJournalTO result){
        //TODO upload word template as action
        createJournalDocument.createOfNotExists();
        LanguagePairTO languagePairTO = findLanguagePair.byId(result.languagePairId());

        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(DocumentCode.REPORT_FOR_JOURNAL)
                .translationJournalDataSetFrom(result.translationJournalList())
                .languagePairDataSetFrom(languagePairTO)
                .journalStatisticsDataSetFrom(journalStatisticsAggregator.forResult(result))
                .userDataSetFrom(findUser.byUserId(result.userId()))
                .buildConfig()
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .withTaal("DUTCH")
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .build();


        return generateDocument.forDocument(briefConfigTO);
    }

}

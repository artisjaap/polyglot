package be.artisjaap.polyglot.core.action.documents;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.JournalStatisticsAggregator;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentCode;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentForCodeFactory;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeReportTO;
import be.artisjaap.polyglot.core.action.user.FindUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class GenerateReportForJournal {
    @Resource
    private GenerateDocument generateDocument;

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private DocumentForCodeFactory documentForCodeFactory;

    @Resource
    private FindUser findUser;

    @Resource
    private JournalStatisticsAggregator journalStatisticsAggregator;

    @Autowired
    private JournalPracticeResults journalPracticeResults;

    public Optional<byte[]> withData(LanguagePracticeReportTO result) {


        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(JournalFilterTO.builder()
                .from(result.getFrom())
                .languagePairId(result.getLanguagePairId())
                .lessonId(result.getLessonId())
                .until(result.getUntil())
                .userId(result.getUserId())
                .build());


        LanguagePairTO languagePairTO = findLanguagePair.byId(result.getLanguagePairId());

        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(DocumentCode.REPORT_FOR_JOURNAL)
                .translationJournalDataSetFrom(journalFor.translationJournalList())
                .languagePairDataSetFrom(languagePairTO)
                .journalStatisticsDataSetFrom(journalStatisticsAggregator.forResult(journalFor))
                .userDataSetFrom(findUser.byUserId(result.getUserId()))
                .buildConfig()
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorDB())
                .taal("DUTCH")
                .code(DocumentCode.REPORT_FOR_JOURNAL.name())
                .build();


        return generateDocument.forDocument(briefConfigTO);
    }

}

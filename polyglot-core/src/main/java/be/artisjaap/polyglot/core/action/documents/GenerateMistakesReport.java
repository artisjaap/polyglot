package be.artisjaap.polyglot.core.action.documents;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentCode;
import be.artisjaap.polyglot.core.action.documents.docconfig.DocumentForCodeFactory;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeMistakesReportTO;
import be.artisjaap.polyglot.core.action.user.FindUser;
import com.beust.jcommander.internal.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenerateMistakesReport {

    private final FindLanguagePair findLanguagePair;
    private final GenerateDocument generateDocument;
    private final DocumentForCodeFactory documentForCodeFactory;
    private final FindUser findUser;
    private final JournalPracticeResults journalPracticeResults;

    public GenerateMistakesReport(FindLanguagePair findLanguagePair,
                                  GenerateDocument generateDocument,
                                  DocumentForCodeFactory documentForCodeFactory,
                                  FindUser findUser,
                                  JournalPracticeResults journalPracticeResults){
        this.findLanguagePair = findLanguagePair;
        this.generateDocument = generateDocument;
        this.documentForCodeFactory = documentForCodeFactory;
        this.findUser = findUser;
        this.journalPracticeResults = journalPracticeResults;
    }

    public Optional<byte[]> withData(LanguagePracticeMistakesReportTO data) {
        LanguagePairTO languagePairTO = findLanguagePair.byId(data.getLanguagePairId());
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(JournalFilterTO.builder()
                .from(data.getFrom())
                .languagePairId(data.getLanguagePairId())
                .lessonId(data.getLessonId())
                .until(data.getUntil())
                .userId(data.getUserId())
                .errorsOnly(true)
                .build());

        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(DocumentCode.PRACTICE_MISTAKES)
                .translationJournalDataSetFrom(journalFor.translationJournalList())
                .languagePairDataSetFrom(languagePairTO)
                .userDataSetFrom(findUser.byUserId(data.getUserId()))
                .buildConfig()
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorDB())
                .taal("nld")
                .code(DocumentCode.PRACTICE_MISTAKES.name())
                .build();

        return generateDocument.forDocument(briefConfigTO);
    }
}

package be.artisjaap.polyglot.core.action.journal;

import be.artisjaap.common.utils.InfinitRandomDataStreamer;
import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.documents.DatasetProviderFactory;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.CreateJournalPdfTO;
import be.artisjaap.polyglot.core.action.to.CreatePracticePdfTO;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CreateJournalPdf {

    private JournalPracticeResults journalPracticeResults;
    private FindLanguagePair findLanguagePair;
    private GenerateDocument generateDocument;

    public CreateJournalPdf(JournalPracticeResults journalPracticeResults){
        this.journalPracticeResults = journalPracticeResults;
    }


    public Optional<byte[]> forData(CreateJournalPdfTO journalPdfTO){
        LanguagePracticeJournalTO journalData = journalPracticeResults.findJournalFor(JournalFilterTO.newBuilder()
                .withLanguagePairId(journalPdfTO.getLanguagePairId())
                .withFrom(journalPdfTO.getFrom())
                .withUntil(journalPdfTO.getTo())
                .withLessonId(journalPdfTO.getLessonId())
                .withUserId(journalPdfTO.getUserId())
                .build());



        LanguagePairTO languagePairTO = findLanguagePair.byId(journalPdfTO.getLanguagePairId());

        return generateDocument.forDocument(BriefConfigTO.builder()
                .code("JOURNAL_TRANSLATIONS_RESULT")
                .datasetProvider(DatasetProviderFactory.create()
                        .journalStatisticsDataSetFrom(journalData.calculateStatistics())
                        .journalTranslations(journalData.translationJournalList())
                        .languagePairDataSetFrom(languagePairTO))
                .opslagSettingsTO(BriefLocatieFactory.briefNietOpslaan())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .taal("NL")
                .build());

    }

}

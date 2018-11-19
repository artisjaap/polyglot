package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.core.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.GenerateReportForJournal;
import be.artisjaap.polyglot.core.action.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePracticeJournalResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api/journal")
public class LanguagePairJournalController {

    @Autowired
    private JournalPracticeResults journalPracticeResults;

    @Autowired
    private GenerateReportForJournal generateReportForJournal;

    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/year-month/{yearMonth}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserInYearMonth(@PathVariable String userId, @PathVariable String translationPairId, @PathVariable String yearMonth) throws IOException {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(userId, translationPairId, LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth));

        Optional<byte[]> bytes = generateReportForJournal.withData(journalFor);
        FileUtils.writeByteArrayToFile(new File("c:/temp/test.pdf"), bytes.get());

        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }



    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/date/{date}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserOnDate(@PathVariable String userId,
                                                                                      @PathVariable String translationPairId,
                                                                                      @PathVariable String date) {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(userId, translationPairId, LocalDateUtils.parseDateFromYYYYMMDDString(date));
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }

    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserAndLesson(@PathVariable String userId,
                                                                                      @PathVariable String lessonId) {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId);
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }

    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}/year-month/{yearMonth}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findJournalForUserAndLessonInYearMonth(@PathVariable String userId,
                                                                                      @PathVariable String lessonId,
                                                                                      @PathVariable String yearMonth) {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId, LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth));
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }

    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}/date/{date}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findJournalForUserAndLessonOnDay(@PathVariable String userId,
                                                                                      @PathVariable String lessonId,
                                                                                      @PathVariable String date) {

        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId, LocalDateUtils.parseDateFromYYYYMMDDString(date));
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }



}

/*
        LanguagePair languagePair = languagePairPersister.randomLanguagePair();
        Translation translation = translationPersister.randomTranslationForLanguagePair(languagePair);

        Optional<byte[]> bytes = generateReportForJournal.withData(JournalResultTO.newBuilder()
                .withLanguagePairId(languagePair.getId().toString())
                .withAnswerTOList(Arrays.asList(AnswerJournalTO.newBuilder()
                        .withTranslationId(translation.getId().toString())
                        .withQuestion(translation.getLanguageA())
                        .withGivenAnswer(translation.getLanguageB())
                        .withExpectedAnswer(translation.getLanguageB())
                        .withCorrect(false)
                        .build(),AnswerJournalTO.newBuilder()
                                .withTranslationId(translation.getId().toString())
                                .withQuestion(translation.getLanguageA())
                                .withGivenAnswer(translation.getLanguageB())
                                .withExpectedAnswer(translation.getLanguageB())
                                .withCorrect(true)
                                .build())
                        )
                .build());
        FileUtils.writeByteArrayToFile(new File("c:/temp/test.pdf"), bytes.get());
 */
package be.artisjaap.polyglot.web.endpoints.old;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.core.utils.WebUtils;
import be.artisjaap.polyglot.core.action.documebts.GenerateReportForJournal;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.web.endpoints.old.request.JournalReportRequest;
import be.artisjaap.polyglot.web.endpoints.old.response.LanguagePracticeJournalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

//@Controller
//@RequestMapping("/api/journal")
public class LanguagePairJournalController {

    @Autowired
    private JournalPracticeResults journalPracticeResults;

    @Autowired
    private GenerateReportForJournal generateReportForJournal;

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForFilter(@RequestBody JournalReportRequest reportRequest)  {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(JournalFilterTO.newBuilder()
                .withFrom(LocalDateUtils.parseDateFromYYYYMMDDString(reportRequest.getFrom()))
                .withLanguagePairId(reportRequest.getLanguagePairId())
                .withLessonId(reportRequest.getLessonId())
                .withUntil(LocalDateUtils.parseDateFromYYYYMMDDString(reportRequest.getUntil()))
                .withUserId(reportRequest.getUserId())
                .build());
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }

    @RequestMapping(value = "/result/pdf", method = RequestMethod.POST)
    public void findAllJournalForFilterToPDF(HttpServletResponse response, @RequestBody JournalReportRequest reportRequest) throws IOException {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(JournalFilterTO.newBuilder()
                .withFrom(LocalDateUtils.parseDateFromYYYYMMDDString(reportRequest.getFrom()))
                .withLanguagePairId(reportRequest.getLanguagePairId())
                .withLessonId(reportRequest.getLessonId())
                .withUntil(LocalDateUtils.parseDateFromYYYYMMDDString(reportRequest.getUntil()))
                .withUserId(reportRequest.getUserId())
                .build());
        Optional<byte[]> bytes = generateReportForJournal.withData(journalFor);

        OutputStream outputStream = WebUtils.maakOutputstreamVoorXlsxFile(response, "test");
        outputStream.write(bytes.orElse(null));

        response.flushBuffer();
    }


//    @RequestMapping(value = "/gebruiker/xls-export", method = RequestMethod.POST)
//    public void exportAll(HttpServletResponse response, @RequestBody GebruikerZoekTO filter) throws Exception{
//        OutputStream out = maakOutputstreamVoorXlsxFile(response, "gebruiker-export");
//        Stream<GebruikerTO> atleten = gebruikerService.findGebruikersFor(filter);
//        XlsExport.forData(atleten, DefaultExporter.create(GebruikerTO.class)).get(out);
//        response.flushBuffer();
//    }

//
//    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/year-month/{yearMonth}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserInYearMonth(@PathVariable String userId, @PathVariable String translationPairId, @PathVariable String yearMonth) throws IOException {
//        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(JournalFilterTO.newBuilder()
//                .withFrom(LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth).atDay(1))
//                .withLanguagePairId(translationPairId)
////                .withLessonId(reportRequest.getLessonId())
//                .withUntil(LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth).atEndOfMonth())
//                .withUserId(userId)
//                .build());
//        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
//    }
//
//
//
//    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/date/{date}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserOnDate(@PathVariable String userId,
//                                                                                      @PathVariable String translationPairId,
//                                                                                      @PathVariable String date) {
//        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(userId, translationPairId, LocalDateUtils.parseDateFromYYYYMMDDString(date));
//        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
//    }
//
//    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserAndLesson(@PathVariable String userId,
//                                                                                      @PathVariable String lessonId) {
//        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId);
//        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
//    }
//
//    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}/year-month/{yearMonth}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<LanguagePracticeJournalResponse> findJournalForUserAndLessonInYearMonth(@PathVariable String userId,
//                                                                                      @PathVariable String lessonId,
//                                                                                      @PathVariable String yearMonth) {
//        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId, LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth));
//        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
//    }
//
//    @RequestMapping(value = "/user/{userId}/lesson/{lessonId}/date/{date}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<LanguagePracticeJournalResponse> findJournalForUserAndLessonOnDay(@PathVariable String userId,
//                                                                                      @PathVariable String lessonId,
//                                                                                      @PathVariable String date) {
//
//        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalForUserAndLesson(userId, lessonId, LocalDateUtils.parseDateFromYYYYMMDDString(date));
//        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
//    }



}

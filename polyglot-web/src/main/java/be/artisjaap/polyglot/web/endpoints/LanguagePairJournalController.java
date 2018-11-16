package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.core.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePracticeJournalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/journal")
public class LanguagePairJournalController {

    @Autowired
    private JournalPracticeResults journalPracticeResults;

    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/year-month/{yearMonth}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllJournalForUserInYearMonth(@PathVariable String userId, @PathVariable String translationPairId, @PathVariable String yearMonth) {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(userId, translationPairId, LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth));
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

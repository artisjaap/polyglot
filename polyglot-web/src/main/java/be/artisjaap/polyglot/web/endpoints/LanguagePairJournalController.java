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

    @RequestMapping(value = "/user/{userId}/translation-pair/{translationPairId}/{yearMonth}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> findAllLanguagePairsForUser(@PathVariable String userId, @PathVariable String translationPairId, @PathVariable String yearMonth) {
        LanguagePracticeJournalTO journalFor = journalPracticeResults.findJournalFor(userId, translationPairId, LocalDateUtils.parseYearMonthFromYYYYMMString(yearMonth));
        return ResponseEntity.ok(LanguagePracticeJournalResponse.from(journalFor));
    }

}

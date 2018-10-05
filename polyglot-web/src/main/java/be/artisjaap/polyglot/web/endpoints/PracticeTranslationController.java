package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.PracticeWords;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.web.endpoints.response.PracticeWordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translations/practice")
public class PracticeTranslationController {

    @Autowired
    private PracticeWords practiceWords;


    @RequestMapping(value = "/next/{userId}/{languagePairId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<PracticeWordResponse>> findAllOpenPracticeWords(@PathVariable String userId, @PathVariable String languagePairId, @PathVariable OrderType orderType) {
        List<PracticeWordTO> practiceWordTOS = practiceWords.giveCurrentListToPractice(userId, languagePairId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTOS));
    }
}

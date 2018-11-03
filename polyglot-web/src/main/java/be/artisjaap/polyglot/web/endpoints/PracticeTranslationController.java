package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.PracticeWords;
import be.artisjaap.polyglot.core.action.PracticeWordsFiltered;
import be.artisjaap.polyglot.core.action.SimpleNextWordStrategy;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.web.endpoints.request.PracticeWordCheckRequest;
import be.artisjaap.polyglot.web.endpoints.request.TranslationsFilterRequest;
import be.artisjaap.polyglot.web.endpoints.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/translations/practice")
public class PracticeTranslationController {

    @Autowired
    private PracticeWords practiceWords;

    @Autowired
    private PracticeWordsFiltered practiceWordsFiltered;

    @Autowired
    private SimpleNextWordStrategy simpleNextWordStrategy;


    @RequestMapping(value = "/list/{userId}/{languagePairId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<PracticeWordResponse>> findAllOpenPracticeWords(@PathVariable String userId, @PathVariable String languagePairId, @PathVariable OrderType orderType) {
        List<PracticeWordTO> practiceWordTOS = simpleNextWordStrategy.giveCurrentListToPractice(userId, languagePairId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTOS));
    }

    @RequestMapping(value = "/list/all/filterd", method = RequestMethod.POST)
    public @ResponseBody
      ResponseEntity<PagedResponse<TranslationResponse>> findAllPracticeWordsFiltered(@RequestBody TranslationsFilterRequest translationsFilterRequest) {
        PagedTO<TranslationTO> translationTOPagedTO = practiceWordsFiltered.withFilter(TranslationFilterTO.newBuilder()
                .withLanguagePairId(translationsFilterRequest.getLanguagePairId())
                .withPage(translationsFilterRequest.getPageNumber())
                .withPageSize(translationsFilterRequest.getPageSize())
                .withTextFilter(translationsFilterRequest.getTextFilter())
                .build());
        PagedResponse<TranslationResponse> response = PagedResponse.from(translationTOPagedTO, t-> TranslationResponse.from(t));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/next/{userId}/{languagePairId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> nextPracticeWord(@PathVariable String userId, @PathVariable String languagePairId, @PathVariable OrderType orderType) {
        PracticeWordTO practiceWordTO = practiceWords.nextWord(userId, languagePairId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTO));
    }

    @RequestMapping(value = "/check-and-next", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AnswerAndNextWordResponse> nextCheckAnswerAndGiveNext(@RequestBody PracticeWordCheckRequest practiceWordCheck) {
        PracticeWordCheckTO practiceWordCheckTO = PracticeWordCheckTO.newBuilder()
                .withAnswerGiven(practiceWordCheck.getAnswerGiven())
                .withAnswerOrderType(practiceWordCheck.getAnswerOrderType())
                .withNextOrderType(practiceWordCheck.getNextOrderType())
                .withTranslationId(practiceWordCheck.getTranslationId())
                .withUserId(practiceWordCheck.getUserId())
                .build();

        AnswerAndNextWordTO answerAndNextWordTO = practiceWords.checkAnswerAndGiveNext(practiceWordCheckTO);
        return ResponseEntity.ok(AnswerAndNextWordResponse.from(answerAndNextWordTO));
    }



}

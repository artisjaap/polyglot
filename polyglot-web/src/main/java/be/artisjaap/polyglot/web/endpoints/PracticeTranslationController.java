package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.lesson.FindPracticeWord;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWords;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWordsFiltered;
import be.artisjaap.polyglot.core.action.lesson.SimpleNextWordStrategy;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.action.translation.UpdateStatusTranslation;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.web.endpoints.request.PracticeWordCheckRequest;
import be.artisjaap.polyglot.web.endpoints.request.TranslationsFilterRequest;
import be.artisjaap.polyglot.web.endpoints.response.AnswerAndNextWordResponse;
import be.artisjaap.polyglot.web.endpoints.response.PagedResponse;
import be.artisjaap.polyglot.web.endpoints.response.PracticeWordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translations/practice")
public class PracticeTranslationController {

    @Autowired
    private FindPracticeWords findPracticeWords;

    @Autowired
    private FindPracticeWordsFiltered findPracticeWordsFiltered;

    @Autowired
    private SimpleNextWordStrategy simpleNextWordStrategy;

    @Autowired
    private UpdateStatusTranslation updateStatusTranslation;

    @Autowired
    private FindPracticeWord findPracticeWord;

    @RequestMapping(value = "/list/{userId}/{languagePairId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<PracticeWordResponse>> findAllOpenPracticeWords(@PathVariable String userId, @PathVariable String languagePairId, @PathVariable OrderType orderType) {
        List<PracticeWordTO> practiceWordTOS = simpleNextWordStrategy.giveCurrentListToPractice(userId, languagePairId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTOS));
    }

    @RequestMapping(value = "/list/all/filterd", method = RequestMethod.POST)
    public @ResponseBody
      ResponseEntity<PagedResponse<PracticeWordResponse>> findAllPracticeWordsFiltered(@RequestBody TranslationsFilterRequest translationsFilterRequest) {
        PagedTO<PracticeWordTO> translationTOPagedTO = findPracticeWordsFiltered.withFilter(TranslationFilterTO.newBuilder()
                .withLanguagePairId(translationsFilterRequest.getLanguagePairId())
                .withPage(translationsFilterRequest.getPageNumber())
                .withPageSize(translationsFilterRequest.getPageSize())
                .withTextFilter(translationsFilterRequest.getTextFilter())
                .build());

        PagedResponse<PracticeWordResponse> response = PagedResponse.from(translationTOPagedTO, t-> PracticeWordResponse.from(t));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/next/{userId}/{languagePairId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> nextPracticeWord(@PathVariable String userId, @PathVariable String languagePairId, @PathVariable OrderType orderType) {
        PracticeWordTO practiceWordTO = findPracticeWords.nextWord(userId, languagePairId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTO));
    }

    @RequestMapping(value = "/check-and-next", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AnswerAndNextWordResponse> nextCheckAnswerAndGiveNext(@RequestBody PracticeWordCheckRequest practiceWordCheck) {
        PracticeWordCheckTO practiceWordCheckTO = PracticeWordCheckTO.newBuilder()
                .withLessonId(practiceWordCheck.getLessonId())
                .withAnswerGiven(practiceWordCheck.getAnswerGiven())
                .withAnswerOrderType(practiceWordCheck.getAnswerOrderType())
                .withNextOrderType(practiceWordCheck.getNextOrderType())
                .withTranslationId(practiceWordCheck.getTranslationId())
                .withUserId(practiceWordCheck.getUserId())
                .build();

        AnswerAndNextWordTO answerAndNextWordTO = findPracticeWords.checkAnswerAndGiveNext(practiceWordCheckTO);
        return ResponseEntity.ok(AnswerAndNextWordResponse.from(answerAndNextWordTO));
    }

    @RequestMapping(value = "/update-status/{translationId}/{status}", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> updateStatus(@PathVariable String translationId, @PathVariable ProgressStatus status) {
        updateStatusTranslation.toStatus(translationId, status);
        return ResponseEntity.ok(PracticeWordResponse.from(findPracticeWord.forTranslation(translationId, OrderType.NORMAL)));
    }

    @RequestMapping(value = "/update-status-to-next/{translationId}", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> updateStatusToNext(@PathVariable String translationId) {
        PracticeWordTO practiceWord = findPracticeWord.forTranslation(translationId, OrderType.NORMAL);
        ProgressStatus progressStatus = practiceWord.wordStatsTO().progressStatus().logicalNextStatus();
        return updateStatus(translationId, progressStatus);
    }

}



package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.lesson.FindPracticeWord;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWords;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordCheckTO;
import be.artisjaap.polyglot.web.endpoints.request.NewLanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.request.PracticeAnswerValidateRequest;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.endpoints.response.PracticeAnswerResponse;
import be.artisjaap.polyglot.web.endpoints.response.PracticeAnswerResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class PracticeTranslationController extends BaseController{
    @Resource
    private FindPracticeWords findPracticeWords;

    @Resource
    private PracticeAnswerResponseMapper practiceAnswerResponseMapper;

    @RequestMapping(value = "/practice/validate", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<PracticeAnswerResponse> createLanguagePair(@RequestBody PracticeAnswerValidateRequest practiceAnswerValidateRequest) {
        AnswerTO answerTO = findPracticeWords.checkAnswer(PracticeWordCheckTO.newBuilder()
                .withAnswerGiven(practiceAnswerValidateRequest.getAnswerGiven())
                .withNextOrderType(practiceAnswerValidateRequest.getAnswerOrderType())
                .withTranslationId(practiceAnswerValidateRequest.getTranslationId())
                .withUserId(userId())
                .withAnswerOrderType(practiceAnswerValidateRequest.getAnswerOrderType())
                .withLessonId(practiceAnswerValidateRequest.getLessonId())
                .build());

        return ResponseEntity.ok(practiceAnswerResponseMapper.map(answerTO));
    }
}

package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.lessonpractice.DoLessonPractice;
import be.artisjaap.polyglot.core.action.to.AnswerAndNextWordTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordCheckTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.web.endpoints.old.request.PracticeWordCheckRequest;
import be.artisjaap.polyglot.web.endpoints.old.response.AnswerAndNextWordResponse;
import be.artisjaap.polyglot.web.endpoints.old.response.PracticeWordResponse;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class LessonPracticeController {

    @Resource
    private DoLessonPractice doLessonPractice;

    

    @RequestMapping(value = "/practice-lesson/next/{lessonId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> nextPracticeWord( @PathVariable String lessonId, @PathVariable OrderType orderType) {
        PracticeWordTO practiceWordTO = this.doLessonPractice.nextWordForLesson(lessonId, orderType);
        return ResponseEntity.ok(PracticeWordResponse.from(practiceWordTO));
    }

    @RequestMapping(value = "/practice-lesson/{lessonId}/reset", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> resetLessonPractice(@PathVariable String lessonId) {
        this.doLessonPractice.resetLessonPractice(lessonId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/practice-lesson/check-and-next", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AnswerAndNextWordResponse> checkAndNext(@RequestBody PracticeWordCheckRequest practiceWordCheck) {
        PracticeWordCheckTO practiceWordCheckTO = PracticeWordCheckTO.newBuilder()
                .withLessonId(practiceWordCheck.getLessonId())
                .withAnswerGiven(practiceWordCheck.getAnswerGiven())
                .withAnswerOrderType(practiceWordCheck.getAnswerOrderType())
                .withNextOrderType(practiceWordCheck.getNextOrderType())
                .withTranslationId(practiceWordCheck.getTranslationId())
                .withUserId(SecurityUtils.userId())
                .withNormalized(practiceWordCheck.getNormalized())
                .build();
        AnswerAndNextWordTO answerAndNextWordTO = this.doLessonPractice.evaluateResultAndGetNext(practiceWordCheckTO);
        return ResponseEntity.ok(AnswerAndNextWordResponse.from(answerAndNextWordTO));
    }

    @RequestMapping(value = "/practice-lesson/{lessonId}/reset", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> resetPracticeLesson(@PathVariable String lessonId) {
        this.doLessonPractice.resetLessonPractice(lessonId);
        return ResponseEntity.ok().build();
    }


}

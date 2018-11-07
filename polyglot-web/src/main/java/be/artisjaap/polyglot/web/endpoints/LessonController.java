package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.CreateLesson;
import be.artisjaap.polyglot.core.action.FindLesson;
import be.artisjaap.polyglot.core.action.TestForLesson;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.NewAutomaticLessonTO;
import be.artisjaap.polyglot.core.action.to.test.*;
import be.artisjaap.polyglot.web.endpoints.request.NewAutomaticLessonRequest;
import be.artisjaap.polyglot.web.endpoints.request.TestSolutionRequest;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponse;
import be.artisjaap.polyglot.web.endpoints.response.TestAssignmentResponse;
import be.artisjaap.polyglot.web.endpoints.response.TestResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @Autowired
    private CreateLesson createLesson;

    @Autowired
    private FindLesson findLesson;

    @Autowired
    private TestForLesson testForLesson;

    @RequestMapping(value = "/autocreate", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LessonResponse> automaticLesson(@RequestBody NewAutomaticLessonRequest automaticLessonRequest) {
        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withUserId(automaticLessonRequest.getUserId())
                .withMaxNumberOfWords(automaticLessonRequest.getMaxNumberOfWords())
                .withLessonTitle(automaticLessonRequest.getLessonTitle())
                .withLanguagePairId(automaticLessonRequest.getLanguagePairId())
                .build());

        return ResponseEntity.ok(LessonResponse.from(lessonTO));
    }

    @RequestMapping(value = "/practice/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonForPracticing(@PathVariable String lessonId) {
        return ResponseEntity.ok(LessonResponse.from(findLesson.forPracticing(lessonId)));
    }

    @RequestMapping(value = "/test/{lessonId}/{orderType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<TestAssignmentResponse> lessonForTesting(@PathVariable String lessonId, @PathVariable OrderType orderType) {
        TestAssignmentTO testAssignmentTO = testForLesson.asSimpleTestForLesson(CreateTestTO.newBuilder()
                .withOrderType(orderType)
                .withLessonId(lessonId)
                .build());
        //FIXME result should not include the answer to the question
        return ResponseEntity.ok(TestAssignmentResponse.from(testAssignmentTO));

    }

    @RequestMapping(value = "/test/correct", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TestResultResponse> lessonForTesting(@RequestBody TestSolutionRequest testSolutionRequest) {
        TestSolutionTO testSolutionTO = TestSolutionTO.newBuilder()
                .withUserId(testSolutionRequest.getUserId())
                .withSolutions(testSolutionRequest.getSolutions().stream()
                        .map(s -> WordSolutionTO.newBuilder()
                                .withTranslationId(s.getTranslationId())
                                .withAnswer(s.getAnswer())
                                .withQuestion(s.getQuestion())
                                .withAnswerLanguage(s.getAnswerLanguage())
                                .build()).collect(Collectors.toList()))
                .withLessonId(testSolutionRequest.getLessonId())
                .build();
        return ResponseEntity.ok(TestResultResponse.from(testForLesson.correctTest(testSolutionTO)));

    }



}

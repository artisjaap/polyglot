package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.lesson.FindLesson;
import be.artisjaap.polyglot.core.action.lesson.LessonsFiltered;
import be.artisjaap.polyglot.core.action.lesson.TestForLesson;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.*;
import be.artisjaap.polyglot.web.endpoints.request.*;
import be.artisjaap.polyglot.web.endpoints.response.*;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private LessonsFiltered lessonsFiltered;

    /* endpoints to define

    GET /api/lessons - all lessons headers of logged in user
    GET /api/lessons/:id - lesson details for specified id for current user
    POST /api/lesson/autocreate - maak automatische les
    POST /api/lesson/create - maak les
    GET /api/lesson/:id/practice - practice the given lesson
    GET /api/lesson?languagePairId - lesson for specified languagepair
     */

    /// used in front

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> allMyLessons() {
        return ResponseEntity.ok(LessonHeaderResponse.from(findLesson.forUser(SecurityUtils.userId())));

    }


    /// not yet used in front

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


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LessonResponse> create(@RequestBody NewLessonRequest newLessonRequest) {
        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withUserId(newLessonRequest.getUserId())
                .withLessonTitle(newLessonRequest.getLessonTitle())
                .withLanguagePairId(newLessonRequest.getLanguagePairId())
                .build());

        return ResponseEntity.ok(LessonResponse.from(lessonTO));
    }

    @RequestMapping(value = "/practice/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonForPracticing(@PathVariable String lessonId) {
        return ResponseEntity.ok(LessonResponse.from(findLesson.forPracticing(lessonId)));
    }

    @RequestMapping(value = "/{languagePairId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> allLessonsForLanguagePair(@PathVariable String languagePairId) {
        return ResponseEntity.ok(LessonHeaderResponse.from(findLesson.forLanguagePair(languagePairId)));
    }

    @RequestMapping(value = "/list/all/filtered", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<PagedResponse<LessonHeaderResponse>> findAllPracticeWordsFiltered(@RequestBody LessonsFilterRequest lessonsFilterRequest) {
        PagedTO<LessonHeaderTO> lessonHeaderTOPagedTO = lessonsFiltered.withFilter(LessonFilterTO.newBuilder()
                .withLanguagePairId(lessonsFilterRequest.getLanguagePairId())
                .withPage(lessonsFilterRequest.getPageNumber())
                .withPageSize(lessonsFilterRequest.getPageSize())
                .withTextFilter(lessonsFilterRequest.getTextFilter())
                .build());

        PagedResponse<LessonHeaderResponse> response = PagedResponse.from(lessonHeaderTOPagedTO, t-> LessonHeaderResponse.from(t));
        return ResponseEntity.ok(response);
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

    @RequestMapping(value = "/add/{lessonId}/{translationId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<LessonTranslationPairResponse> addTranslationToLesson(@PathVariable String lessonId, @PathVariable String translationId) {
        return ResponseEntity.ok(LessonTranslationPairResponse.from(createLesson.addTranslationToLesson(lessonId, translationId)));
    }

    @RequestMapping(value = "/remove/{lessonId}/{translationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LessonTranslationPairResponse> removeTranslationToLesson(@PathVariable String lessonId, @PathVariable String translationId) {
        return ResponseEntity.ok(LessonTranslationPairResponse.from(createLesson.removeTranslationFromLesson(lessonId, translationId)));
    }


}

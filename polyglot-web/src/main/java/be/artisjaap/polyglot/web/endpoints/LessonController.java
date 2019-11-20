package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.lesson.*;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.*;
import be.artisjaap.polyglot.web.endpoints.request.*;
import be.artisjaap.polyglot.web.endpoints.response.*;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LessonController {
    @Resource
    private CreateLesson createLesson;

    @Resource
    private FindLesson findLesson;

    @Resource
    private FindLessonHeader findLessonHeader;

    @Resource
    private TestForLesson testForLesson;

    @Resource
    private FindLessonsFiltered findLessonsFiltered;

    @Resource
    private CreateNewWordForLesson createNewWordForLesson;

    /* endpoints to define

    GET /api/lessons - all lessons headers of logged in user
    GET /api/lessons/:id - lesson details for specified id for current user
    POST /api/lesson/autocreate - maak automatische les
    POST /api/lesson/create - maak les
    GET /api/lesson/:id/practice - practice the given lesson
    GET /api/lesson?languagePairId - lesson for specified languagepair
     */

    /// used in front
    @RequestMapping(value = "/lesson/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonDetail(@PathVariable String lessonId) {
        LessonTO lessonTO = findLesson.forPracticing(lessonId);
        return ResponseEntity.ok(LessonResponse.from(lessonTO));
    }

    @RequestMapping(value = "/lessons", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LessonResponse> create(@RequestBody NewLessonRequest newLessonRequest) {
        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withUserId(SecurityUtils.userId())
                .withLessonTitle(newLessonRequest.getLessonTitle())
                .withLanguagePairId(newLessonRequest.getLanguagePairId())
                .build());

        return ResponseEntity.ok(LessonResponse.from(lessonTO));
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




    @RequestMapping(value = "/practice/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonForPracticing(@PathVariable String lessonId) {
        return ResponseEntity.ok(LessonResponse.from(findLesson.forPracticing(lessonId)));
    }

    @RequestMapping(value = "/{languagePairId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> allLessonsForLanguagePair(@PathVariable String languagePairId) {
        return ResponseEntity.ok(LessonHeaderResponse.from(findLessonHeader.forLanguagePair(languagePairId)));
    }

    @RequestMapping(value = "/list/all/filtered", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<PagedResponse<LessonHeaderResponse>> findAllPracticeWordsFiltered(@RequestBody LessonsFilterRequest lessonsFilterRequest) {
        PagedTO<LessonHeaderTO> lessonHeaderTOPagedTO = findLessonsFiltered.withFilter(LessonFilterTO.newBuilder()
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

    @RequestMapping(value = "/lesson/{lessonId}/translation", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<TranslationResponse> addNewTranslationToLesson(@PathVariable String lessonId, @RequestBody NewSimpleTranslationRequest translation) {
        TranslationTO translationTO = createNewWordForLesson.forWord(NewWordForLessonTO.newBuilder().lessonId(lessonId)
                .languageFrom(translation.getQuestion())
                .languageFrom(translation.getSolution())
                .build());

        return ResponseEntity.ok(TranslationResponse.from(translationTO));
    }

    @RequestMapping(value = "lesson/{lessonId}/{translationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LessonTranslationPairResponse> removeTranslationToLesson(@PathVariable String lessonId, @PathVariable String translationId) {
        return ResponseEntity.ok(LessonTranslationPairResponse.from(createLesson.removeTranslationFromLesson(lessonId, translationId)));
    }


}

package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.lesson.FindLessonHeader;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.NewLessonTO;
import be.artisjaap.polyglot.web.endpoints.request.NewLessonHeaderRequest;
import be.artisjaap.polyglot.web.endpoints.response.LessonHeaderResponse;
import be.artisjaap.polyglot.web.endpoints.response.LessonHeaderResponseLessonMapper;
import be.artisjaap.polyglot.web.endpoints.response.LessonHeaderResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LessonHeaderController extends BaseController {
    @Resource
    private FindLessonHeader findLessonHeader;

    @Resource
    private LessonHeaderResponseMapper lessonHeaderResponseMapper;

    @Resource
    private LessonHeaderResponseLessonMapper lessonHeaderResponseLessonMapper;

    @Resource
    private CreateLesson createLesson;

    @RequestMapping(value = "/lesson-headers", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> lessonHeadersForLanguagePair(@RequestParam String languagePairId) {
        List<LessonHeaderResponse> lessonHeaderResponses = lessonHeaderResponseMapper.mapToResponse(findLessonHeader.forLanguagePair(languagePairId));
        return ResponseEntity.ok(lessonHeaderResponses);

    }

    @RequestMapping(value = "/lesson-header", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<LessonHeaderResponse> addNewLessonHeader(@RequestBody NewLessonHeaderRequest newLessonHeaderRequest) {
        LessonTO lessonTO = createLesson.create(NewLessonTO.newBuilder()
                .withLanguagePairId(newLessonHeaderRequest.getLanguagePairId())
                .withName(newLessonHeaderRequest.getName())
                .withUserId(userId())
                .build());

        return ResponseEntity.ok(lessonHeaderResponseLessonMapper.map(lessonTO));
    }
}

package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.CreateLesson;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.NewAutomaticLessonTO;
import be.artisjaap.polyglot.web.endpoints.request.NewAutomaticLessonRequest;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @Autowired
    private CreateLesson createLesson;

    @RequestMapping(value = "/pairs", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LessonResponse> createNewPairForUser(@RequestBody NewAutomaticLessonRequest automaticLessonRequest) {
        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withUserId(automaticLessonRequest.getUserId())
                .withMaxNumberOfWords(automaticLessonRequest.getMaxNumberOfWords())
                .withLessonTitle(automaticLessonRequest.getLessonTitle())
                .withLanguagePairId(automaticLessonRequest.getLanguagePairId())
                .build());

        return ResponseEntity.ok(LessonResponse.from(lessonTO));
    }

}

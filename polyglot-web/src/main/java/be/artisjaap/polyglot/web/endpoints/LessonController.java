package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.lesson.FindLesson;
import be.artisjaap.polyglot.core.action.lesson.UpdateLesson;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponse;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponseMapper;
import be.artisjaap.polyglot.web.endpoints.response.TranslationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Resource
    private FindLesson findLesson;

    @Resource
    private LessonResponseMapper lessonResponseMapper;

    @Resource
    private UpdateLesson updateLesson;

    @RequestMapping(value = "/lesson/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonDetailForLesson(@PathVariable String lessonId) {
        LessonTO lessonTO = findLesson.forPracticing(lessonId);
        return ResponseEntity.ok(lessonResponseMapper.map(lessonTO));

    }


    @RequestMapping(value = "/lesson/{lessonId}/translation/{translationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LessonResponse> deleteTranslationFromLesson(@PathVariable String lessonId, @PathVariable String translationId) {
        LessonTO lessonTO = updateLesson.removeTranslationsFromLesson(lessonId, translationId);
        return ResponseEntity.ok(lessonResponseMapper.map(lessonTO));

    }

}

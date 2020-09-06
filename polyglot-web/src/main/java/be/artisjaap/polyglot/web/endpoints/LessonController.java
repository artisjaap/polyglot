package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.core.utils.WebUtils;
import be.artisjaap.polyglot.core.action.lesson.CreateFileForLesson;
import be.artisjaap.polyglot.core.action.lesson.DeleteLesson;
import be.artisjaap.polyglot.core.action.lesson.FindLesson;
import be.artisjaap.polyglot.core.action.lesson.UpdateLesson;
import be.artisjaap.polyglot.core.action.to.CreatePracticePdfTO;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.web.endpoints.request.CreatePracticePdfRequest;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponse;
import be.artisjaap.polyglot.web.endpoints.response.LessonResponseMapper;
import be.artisjaap.polyglot.web.endpoints.response.TranslationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Resource
    private FindLesson findLesson;

    @Resource
    private DeleteLesson deleteLesson;

    @Resource
    private LessonResponseMapper lessonResponseMapper;

    @Resource
    private UpdateLesson updateLesson;

    @Resource
    private CreateFileForLesson createFileForLesson;

    @RequestMapping(value = "/lesson/{lessonId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LessonResponse> lessonDetailForLesson(@PathVariable String lessonId) {
        LessonTO lessonTO = findLesson.forPracticing(lessonId);
        return ResponseEntity.ok(lessonResponseMapper.map(lessonTO));

    }

    @RequestMapping(value = "/lesson/{lessonId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LessonResponse> deleteLesson(@PathVariable String lessonId) {
        LessonTO lessonTO = findLesson.forPracticing(lessonId);
        deleteLesson.deleteLesson(lessonId);
        return ResponseEntity.ok(lessonResponseMapper.map(lessonTO));

    }



    @RequestMapping(value = "/lesson/{lessonId}/translation/{translationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LessonResponse> deleteTranslationFromLesson(@PathVariable String lessonId, @PathVariable String translationId) {
        LessonTO lessonTO = updateLesson.removeTranslationsFromLesson(lessonId, translationId);
        return ResponseEntity.ok(lessonResponseMapper.map(lessonTO));

    }

    @RequestMapping(value = "/lesson/{lessonId}/download", method = RequestMethod.GET)
    public
    void downloadLesson(HttpServletResponse response, @PathVariable String lessonId) throws IOException {
        byte[] data = createFileForLesson.forLessonId(lessonId);


        OutputStream outputStream = WebUtils.maakOutputstreamVoorFile(response, "les", "lesson");
        outputStream.write(data);

        response.flushBuffer();
    }

}

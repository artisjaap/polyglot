package be.artisjaap.polyglot.web.endpoints.old;

import be.artisjaap.polyglot.core.action.lesson.FindLessonHeader;
import be.artisjaap.polyglot.core.action.lesson.FindLessonsFiltered;
import be.artisjaap.polyglot.core.action.to.LessonFilterTO;
import be.artisjaap.polyglot.web.endpoints.old.response.LessonHeaderResponse;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController
//@RequestMapping("/api")
public class LessonHeaderController {
    @Resource
    private FindLessonsFiltered findLessonsFiltered;

    @Resource
    private FindLessonHeader findLessonHeader;

/*
    GET /api/lessons - all lessons headers of logged in user
    GET /api/lessons/:id - lesson details for specified id for current user
    POST /api/lesson/autocreate - maak automatische les
    POST /api/lesson/create - maak les
    GET /api/lesson/:id/practice - practice the given lesson
    GET /api/lesson?languagePairId - lesson for specified languagepair
     */

    @RequestMapping(path= "/lesson-headers", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> allLessonsForUser(@RequestParam String languagePairId) {
        if(languagePairId != null){
            return ResponseEntity.ok(LessonHeaderResponse.from(findLessonsFiltered.withFilter(LessonFilterTO.newBuilder()
                    .withLanguagePairId(languagePairId)
                    .build()).data()));

        }
        return ResponseEntity.ok(LessonHeaderResponse.from(findLessonHeader.forUser(SecurityUtils.userId())));
    }



}
//http://localhost:8080/api/lesson-headers?languagePairId=5dd2e5688607ab1c6090a203
//http://localhost:8080/public/api/lesson-headers?languagePairId=5dd2e5688607ab1c6090a203

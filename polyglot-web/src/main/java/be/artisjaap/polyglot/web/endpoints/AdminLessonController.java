package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.web.endpoints.request.LessonsFilterRequest;
import be.artisjaap.polyglot.web.endpoints.response.LessonHeaderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/all-lessons")
public class AdminLessonController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<LessonHeaderResponse>> allLessonsFiltered(@RequestBody LessonsFilterRequest lessonsFilterRequest) {
       //not yet implemented the, the id is have a filtered list of all available lessons, a AdminLessonHeaderResponse moet gemaakt worden
        //die extra velden bevat zoals van wie is de les, ook de LessonFIlterRequest moet aangepast worden zodat op meer velden kan gefilted worden
        return ResponseEntity.ok().build();


    }
}

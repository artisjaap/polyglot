package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.assembler.LessonAssembler;
import be.artisjaap.polyglot.core.action.assembler.LessonHeaderAssembler;
import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindLesson {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonAssembler lessonAssembler;



    public LessonTO forTesting(String lessonId){
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new ValidationException("LESSON_NOT_FOUND", "les niet gevonden"));
        return lessonAssembler.assembleTOwithoutSolution(lesson);
    }

    public LessonTO forPracticing(String lessonId){
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new ValidationException("LESSON_NOT_FOUND", "les niet gevonden"));
        return lessonAssembler.assembleTO(lesson);
    }


}

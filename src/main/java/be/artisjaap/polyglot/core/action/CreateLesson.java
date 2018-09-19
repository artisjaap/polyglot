package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.LessonAssembler;
import be.artisjaap.polyglot.core.action.assembler.NewLessonAssembler;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.NewLessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateLesson {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonAssembler lessonAssembler;

    @Autowired
    private NewLessonAssembler newLessonAssembler;


    public LessonTO create(NewLessonTO to){
        Lesson lesson = newLessonAssembler.assembleEntity(to);
        lessonRepository.save(lesson);
        return lessonAssembler.assembleTO(lesson);

    }
}

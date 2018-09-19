package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonAssembler implements Assembler<LessonTO, Lesson> {
    @Override
    public LessonTO assembleTO(Lesson entity) {
        return null;
    }

    @Override
    public Lesson assembleEntity(LessonTO lessonTO) {
        return null;
    }
}

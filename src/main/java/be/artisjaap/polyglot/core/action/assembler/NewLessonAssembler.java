package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.NewLessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class NewLessonAssembler implements Assembler<NewLessonTO, Lesson> {
    @Override
    public NewLessonTO assembleTO(Lesson entity) {
        return null;
    }

    @Override
    public Lesson assembleEntity(NewLessonTO newLessonTO) {
        return null;
    }
}

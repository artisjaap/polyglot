package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.assembler.LessonHeaderAssembler;
import be.artisjaap.polyglot.core.action.to.LessonFilterTO;
import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;
import be.artisjaap.polyglot.core.action.to.PagedTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FindLessonsFiltered {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private FindPracticeWord findPracticeWord;

    @Autowired
    private LessonHeaderAssembler lessonHeaderAssembler;

    public PagedTO<LessonHeaderTO> withFilter(LessonFilterTO lessonFilterTO){
        Page<Lesson> lessonsForFilter = lessonRepository.findLessonsForFilter(lessonFilterTO);

        return PagedTO.from(lessonsForFilter, lessonHeaderAssembler);
    }
}

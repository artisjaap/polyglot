package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.LessonFilterTO;
import org.springframework.data.domain.Page;

public interface LessonRepositoryCustom {

    Page<Lesson> findLessonsForFilter(LessonFilterTO filter);

}

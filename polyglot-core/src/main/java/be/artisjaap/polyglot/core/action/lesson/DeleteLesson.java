package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class DeleteLesson {

    private LessonRepository lessonRepository;

    public DeleteLesson(final LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    public void deleteLesson(String lessonId){
        this.lessonRepository.deleteById(new ObjectId(lessonId));


    }
}

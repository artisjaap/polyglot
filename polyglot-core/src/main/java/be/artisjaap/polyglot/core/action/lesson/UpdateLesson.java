package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.assembler.LessonAssembler;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UpdateLesson {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonAssembler lessonAssembler;

    public LessonTO addTranslationsToLesson(String lessonId, Collection<String> translationIds){
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new ValidationException("LESSON_NOT_FOUND"));
        lesson.addTranslations(MongoUtils.toObjectIdSet(translationIds));
        lessonRepository.save(lesson);
        return lessonAssembler.assembleTO(lesson);
    }

    public LessonTO removeTranslationsFromLesson(String lessonId, Collection<String> translationIds){
        Lesson lesson = lessonRepository.findByIdOrThrow(new ObjectId(lessonId));
        lesson.removeTranslations(MongoUtils.toObjectIdSet(translationIds));
        lessonRepository.save(lesson);
        return lessonAssembler.assembleTO(lesson);
    }

}

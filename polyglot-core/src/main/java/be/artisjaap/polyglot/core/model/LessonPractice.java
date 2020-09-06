package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import be.artisjaap.common.utils.ListUtils;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "PolLessonPractice")
@Builder
@Data
public class LessonPractice extends AbstractDocument {



    private ObjectId lessonId;
    private List<LessonPracticeTranslationStatus> translationStatus;
    
    public static LessonPractice createNewForLesson(Lesson lesson){
        return LessonPractice.builder()
                .lessonId(lesson.getId())
                .translationStatus(buildTranslationStatusFor(lesson.getTranslations()))
                .build();
    }

    private static List<LessonPracticeTranslationStatus> buildTranslationStatusFor(Set<ObjectId> translations){
        return translations.stream().map(translation -> LessonPracticeTranslationStatus.builder().status(ProgressStatus.NEW).translationId(translation).build())
                .collect(Collectors.toList());
    }

    public void reset(Lesson lesson) {
        this.setTranslationStatus(buildTranslationStatusFor(lesson.getTranslations()));
    }


}

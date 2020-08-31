package be.artisjaap.polyglot.core.action.lessonpractice;

import be.artisjaap.polyglot.core.action.lesson.FindLesson;
import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;
import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeStatusTO;
import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeTranslationStatusTO;
import be.artisjaap.polyglot.core.model.LessonPractice;
import be.artisjaap.polyglot.core.model.LessonPracticeRepository;
import be.artisjaap.polyglot.core.model.LessonPracticeTranslationStatus;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LessonPracticeStatus {

    private final FindLesson findLesson;
    private final LessonPracticeRepository lessonPracticeRepository;
    private final LessonRepository lessonRepository;
    
    public LessonPracticeStatus(final FindLesson findLesson, 
                                final LessonPracticeRepository lessonPracticeRepository,
                                final LessonRepository lessonRepository){
        this.findLesson = findLesson;
        this.lessonPracticeRepository = lessonPracticeRepository;
        this.lessonRepository = lessonRepository;
    }
    
    public LessonPracticeStatusTO forLesson(String lessonId){
        LessonTO lessonTO = findLesson.forPracticing(lessonId);
        ObjectId lessonObjectId = new ObjectId(lessonId);
        LessonPractice lessonPractice = lessonPracticeRepository.findByLessonId(lessonObjectId)
                .orElseGet(() -> LessonPractice.createNewForLesson(lessonRepository.findById(lessonObjectId).orElseThrow()));

        Map<String, LessonTranslationPairTO> translations = lessonTO.translations().stream().collect(Collectors.groupingBy(LessonTranslationPairTO::translationId, Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), l -> l.stream().findFirst().get())));
        


        List<LessonPracticeTranslationStatusTO> translationStatusses = lessonPractice.getTranslationStatus().stream()
                .map(status -> convertToPracticeTranslation(status, translations.get(status.getTranslationId().toString())))
                .collect(Collectors.toList());

        return LessonPracticeStatusTO.builder().translationStatusses(translationStatusses).build();

    }

    private LessonPracticeTranslationStatusTO convertToPracticeTranslation(LessonPracticeTranslationStatus status, LessonTranslationPairTO translationPairTO) {
        
        return LessonPracticeTranslationStatusTO.builder()
                .asked(status.getAsked())
                .correct(status.getCorrect())
                .percentage(status.percentage())
                .status(status.getStatus())
                .languageA(translationPairTO.question())
                .languageB(translationPairTO.solutions().stream().collect(Collectors.joining(",")))
                .build();

    }
}

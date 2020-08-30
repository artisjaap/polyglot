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
    
    public ObjectId nextTranslationId(){
        return nextTranslationId(null);
    }
    
   
    public ObjectId nextTranslationId(ObjectId previousTranslationId) {
        if(itemsInStatus(ProgressStatus.IN_PROGRESS) < 5L){
            System.out.println("new word < 5");
            return revealNewWord().orElseGet(() -> randomWordInProgress(previousTranslationId));
        }else if(scoreGoodEnough()){
            System.out.println("new word good score");
            return revealNewWord().orElseGet(() -> randomWordInProgress(previousTranslationId));
        }else {
            return randomWordInProgress(previousTranslationId);
        }
    }
    
    public ObjectId updateStatus(ObjectId translationId, boolean correct) {
        translationStatus.stream().filter(translationStatus -> translationStatus.getTranslationId().equals(translationId))
                .findFirst()
                .ifPresent(t -> t.updateWith(correct));
        return nextTranslationId(translationId);
    }

    private boolean scoreGoodEnough() {
        if(translationStatus.stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> translationStatus.getAsked() < 5)
                .findFirst()
                .isPresent()){
            return false;
        }

        Optional<LessonPracticeTranslationStatus> lowRate = translationStatus.stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> translationStatus.percentage() < 50)
                .findFirst();
        if(lowRate
                .isPresent()){
            System.out.println(lowRate.get().percentage());
            return false;
        }
        return true;

    }

    private long itemsInStatus(ProgressStatus status){
        return translationStatus.stream().filter(translationStatus -> translationStatus.getStatus() == status).count();
    }
    
    private ObjectId randomWordInProgress(ObjectId previousTranslationId) {
        List<LessonPracticeTranslationStatus> collect = translationStatus.stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> !translationStatus.getTranslationId().equals(previousTranslationId))
                .sorted(Comparator.comparing(LessonPracticeTranslationStatus::knowledgeStatus))
                .collect(Collectors.toList());

        System.out.println(collect.stream().map(LessonPracticeTranslationStatus::print).collect(Collectors.joining(";")));
        return ListUtils.getRandomElement(collect.stream().limit(5).map(LessonPracticeTranslationStatus::getTranslationId).collect(Collectors.toList()), 0.5);
        
    }

    private Optional<ObjectId> revealNewWord(){
        return translationStatus.stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.NEW)
                .findFirst()
                .map(translationStatus -> {
                    translationStatus.setStatus(ProgressStatus.IN_PROGRESS);
                   return translationStatus.getTranslationId();
                });
    }


    public void reset(Lesson lesson) {
        this.setTranslationStatus(buildTranslationStatusFor(lesson.getTranslations()));
    }
}

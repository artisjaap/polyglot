package be.artisjaap.polyglot.core.action.lessonpractice;

import be.artisjaap.common.utils.ListUtils;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonPractice;
import be.artisjaap.polyglot.core.model.LessonPracticeTranslationStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import org.bson.types.ObjectId;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LessonPracticeEvaluator {


    private final transient long START_NUMBER_OF_WORDS = 5;

    private final transient int MIN_TIMES_KNOWN = 5;
    private final transient int MIN_TIMES_KNOW_PERCENTAGE = 75;

    private final transient int NEW_WORD_MIN_ASKED = 3;
    private final transient int NEW_WORD_MIN_PERCENTAGE = 65;

    private final LessonPractice lessonPractice;

    private LessonPracticeEvaluator(LessonPractice lessonPractice){
        this.lessonPractice = lessonPractice;
    }


    public static LessonPracticeEvaluator forLessonPractice(LessonPractice lessonPractice) {
        return new LessonPracticeEvaluator(lessonPractice);
    }

    public Optional<ObjectId> nextTranslationId(){
        return nextTranslationId(null);
    }


    public Optional<ObjectId> nextTranslationId(ObjectId previousTranslationId) {
        if(itemsInStatus(ProgressStatus.IN_PROGRESS) < START_NUMBER_OF_WORDS){
            return Optional.ofNullable(revealNewWord().orElseGet(() -> randomWordInProgress(previousTranslationId).orElseGet(null)));
        }else if(scoreGoodEnough()){
            return Optional.ofNullable(revealNewWord().orElseGet(() -> randomWordInProgress(previousTranslationId).orElseGet(null)));
        }else {
            return randomWordInProgress(previousTranslationId);
        }
    }

    public Optional<ObjectId> updateStatus(ObjectId translationId, boolean correct) {
        lessonPractice.getTranslationStatus().stream().filter(translationStatus -> translationStatus.getTranslationId().equals(translationId))
                .findFirst()
                .ifPresent(t -> {
                    t.updateWith(correct);
                    t.updateStatus(MIN_TIMES_KNOWN, MIN_TIMES_KNOW_PERCENTAGE);
                });
        return nextTranslationId(translationId);
    }

    private boolean scoreGoodEnough() {
        if(lessonPractice.getTranslationStatus().stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> translationStatus.getAsked() < NEW_WORD_MIN_ASKED)
                .findFirst()
                .isPresent()){
            return false;
        }

        Optional<LessonPracticeTranslationStatus> lowRate = lessonPractice.getTranslationStatus().stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> translationStatus.percentage() < NEW_WORD_MIN_PERCENTAGE)
                .findFirst();
        if(lowRate
                .isPresent()){
            return false;
        }
        return true;

    }

    private long itemsInStatus(ProgressStatus status){
        return lessonPractice.getTranslationStatus().stream().filter(translationStatus -> translationStatus.getStatus() == status).count();
    }

    private Optional<ObjectId> randomWordInProgress(ObjectId previousTranslationId) {
        List<LessonPracticeTranslationStatus> collect = lessonPractice.getTranslationStatus().stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.IN_PROGRESS)
                .filter(translationStatus -> !translationStatus.getTranslationId().equals(previousTranslationId))
                .sorted(Comparator.comparing(LessonPracticeTranslationStatus::knowledgeStatus))
                .collect(Collectors.toList());

        if(collect.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(ListUtils.getRandomElement(collect.stream().limit(5).map(LessonPracticeTranslationStatus::getTranslationId).collect(Collectors.toList()), 0.5));

    }

    private Optional<ObjectId> revealNewWord(){
        return lessonPractice.getTranslationStatus().stream()
                .filter(translationStatus -> translationStatus.getStatus() == ProgressStatus.NEW)
                .findFirst()
                .map(translationStatus -> {
                    translationStatus.setStatus(ProgressStatus.IN_PROGRESS);
                    return translationStatus.getTranslationId();
                });
    }


    public void reset(Lesson lesson) {
        lessonPractice.reset(lesson);
    }
}

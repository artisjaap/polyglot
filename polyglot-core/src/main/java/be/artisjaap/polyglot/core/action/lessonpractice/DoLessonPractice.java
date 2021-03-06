package be.artisjaap.polyglot.core.action.lessonpractice;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.lesson.FindLesson;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWord;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWords;
import be.artisjaap.polyglot.core.action.to.AnswerAndNextWordTO;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordCheckTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeStatusTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonPractice;
import be.artisjaap.polyglot.core.model.LessonPracticeRepository;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class DoLessonPractice {

    private final LessonPracticeRepository lessonPracticeRepository;
    private final LessonRepository lessonRepository;
    private final FindPracticeWords findPracticeWords;
    private final PracticeWordBuilder practiceWordBuilder;
    private final LessonPracticeStatus lessonPracticeStatus;
    
    private DoLessonPractice(LessonPracticeRepository lessonPracticeRepository, 
                             final LessonRepository lessonRepository,
                             final PracticeWordBuilder practiceWordBuilder,
                             final FindPracticeWords findPracticeWords,
                             final LessonPracticeStatus lessonPracticeStatus){
        this.lessonPracticeRepository = lessonPracticeRepository;
        this.lessonRepository = lessonRepository;
        this.findPracticeWords = findPracticeWords;
        this.practiceWordBuilder = practiceWordBuilder;
        this.lessonPracticeStatus = lessonPracticeStatus;
    }
    
    public PracticeWordTO nextWordForLesson(String lessonId, OrderType orderType){
        LessonPractice lessonPractice = findOrCreateLessonPractice(new ObjectId(lessonId));
        LessonPracticeEvaluator lessonPracticeEvaluator = LessonPracticeEvaluator.forLessonPractice(lessonPractice);
        ObjectId translationId = lessonPracticeEvaluator.nextTranslationId();
        lessonPracticeRepository.save(lessonPractice);
        return practiceWordBuilder.forTranslation(translationId.toString(), orderType);
    }
    
    public AnswerAndNextWordTO evaluateResultAndGetNext(PracticeWordCheckTO answer) {
        AnswerTO answerTO = findPracticeWords.checkAnswer(answer);
        LessonPractice lessonPractice = findOrCreateLessonPractice(new ObjectId(answer.lessonId()));
        LessonPracticeEvaluator lessonPracticeEvaluator = LessonPracticeEvaluator.forLessonPractice(lessonPractice);
        ObjectId translationId = lessonPracticeEvaluator.updateStatus(new ObjectId(answerTO.translationId()), answerTO.correctAnswer());
        PracticeWordTO practiceWordTO = practiceWordBuilder.forTranslation(translationId.toString(), answer.nextOrderType());
        lessonPracticeRepository.save(lessonPractice);
        LessonPracticeStatusTO lessonPracticeStatusTO = lessonPracticeStatus.forLesson(answer.lessonId());
        return AnswerAndNextWordTO.newBuilder()
                .withAnswer(answerTO)
                .withPracticeWord(practiceWordTO)
                .withLessonPracticeStatus(lessonPracticeStatusTO)
                .build();
    }
    
    
    
    
    public void resetLessonPractice(String lessonId) {
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new ValidationException("Lesson with ID not found"));
        LessonPractice lessonPractice = findOrCreateLessonPractice(new ObjectId(lessonId));
        lessonPractice.reset(lesson);
        lessonPracticeRepository.save(lessonPractice);
    }
    
    private LessonPractice findOrCreateLessonPractice(ObjectId lessonId) {
        return lessonPracticeRepository.findByLessonId(lessonId)
        .orElseGet(() -> {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ValidationException("Lesson with ID not found"));
            return LessonPractice.createNewForLesson(lesson);
        });
    }
    
}

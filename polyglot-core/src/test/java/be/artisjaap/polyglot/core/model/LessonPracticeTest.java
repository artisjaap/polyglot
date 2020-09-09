package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.testhelper.LessonMother;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class LessonPracticeTest {
    
    @Test
    public void testOrderOfNextWords(){
//        Lesson lesson = LessonMother.initRandom()
//                .withTranslations(IntStream.rangeClosed(1, 10).mapToObj(i -> new ObjectId()).collect(Collectors.toSet()))
//                .build();
//        LessonPractice newForLesson = LessonPractice.createNewForLesson(lesson);
//
//        ObjectId previousTranslationId = newForLesson.nextTranslationId();
//        for (int i = 0; i < 100; i++) {
//            System.out.println(previousTranslationId);
//            previousTranslationId = newForLesson.updateStatus(previousTranslationId, true);
//        }
    }

}

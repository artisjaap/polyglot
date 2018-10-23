package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.Lesson;
import org.bson.types.ObjectId;

import java.util.Arrays;

public class LessonMother extends AbstractMother {

    public static Lesson.Builder initRandom(){
        return Lesson.newBuilder()
                .withUserId(new ObjectId())
                .withName(fairy.textProducer().word())
                .withLanguagePairId(new ObjectId())
                .withTranslations(Arrays.asList(new ObjectId()));
    }
}

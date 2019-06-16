package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.Lesson;
import com.google.common.collect.Sets;
import org.bson.types.ObjectId;

public class LessonMother extends AbstractMother {

    public static Lesson.Builder initRandom(){
        return Lesson.newBuilder()
                .withUserId(new ObjectId())
                .withName(fairy.textProducer().word())
                .withLanguagePairId(new ObjectId())
                .withTranslations(Sets.newHashSet(new ObjectId()));
    }
}

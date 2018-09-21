package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LessonAssembler implements Assembler<LessonTO, Lesson> {
    @Override
    public LessonTO assembleTO(Lesson doc) {
        return LessonTO.newBuilder()
                .forDocument(doc)
                .withName(doc.getName())
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withTranslations(doc.getTranslations().stream().map(ObjectId::toString).collect(Collectors.toList()))
                .withUserId(doc.getUserId())
                .build();
    }

    @Override
    public Lesson assembleEntity(LessonTO lessonTO) {
        return Lesson.newBuilder()
                .forDocument(lessonTO)
                .withName(lessonTO.name())
                .withLanguagePairId(new ObjectId(lessonTO.languagePairId()))
                .withTranslations(lessonTO.translations().stream().map(ObjectId::new).collect(Collectors.toList()))
                .withUserId(new ObjectId(lessonTO.id()))
                .build();
    }
}

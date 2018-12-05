package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;
import be.artisjaap.polyglot.core.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonHeaderAssembler implements Assembler<LessonHeaderTO, Lesson> {
    @Override
    public LessonHeaderTO assembleTO(Lesson doc) {
        return LessonHeaderTO.newBuilder()
                .forDocument(doc)
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withUserId(doc.getUserId().toString())
                .withName(doc.getName())
                .build();
    }

    @Override
    public Lesson assembleEntity(LessonHeaderTO lessonHeaderTO) {
        throw new UnsupportedOperationException("Can't convert lessonHeader to full lesson");
    }
}

package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.core.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.to.NewLessonTO;
import be.artisjaap.polyglot.core.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewLessonAssembler implements Assembler<NewLessonTO, Lesson> {
    @Override
    public NewLessonTO assembleTO(Lesson entity) {
        return null;
    }

    @Override
    public Lesson assembleEntity(NewLessonTO newLessonTO) {
        return Lesson.newBuilder()
                .withLanguagePairId(MongoUtils.toObjectId(newLessonTO.languagePairId()))
                .withUserId(MongoUtils.toObjectId(newLessonTO.userId()))
                .withName(newLessonTO.name())
                .withTranslations(newLessonTO.translationsIds().stream().map(MongoUtils::toObjectId).collect(Collectors.toSet()))
                .build();
    }
}

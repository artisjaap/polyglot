package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.common.utils.MongoUtils;
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
                .withLanguagePairId(MongoUtils.toObjectId(newLessonTO.getLanguagePairId()))
                .withUserId(MongoUtils.toObjectId(newLessonTO.getUserId()))
                .withName(newLessonTO.getName())
                .withTranslations(newLessonTO.getTranslationsIds().stream().map(MongoUtils::toObjectId).collect(Collectors.toSet()))
                .withTags(newLessonTO.getTags())
                .build();
    }
}

package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreateNewWordForLesson {

    @Resource
    private LessonRepository lessonRepository;

    @Resource
    private CreateTranslation createTranslation;

    public TranslationTO forWord(NewWordForLessonTO newWord){
        Lesson lesson = lessonRepository.findById(new ObjectId(newWord.getLessonId())).orElseThrow(() -> new ValidationException("LESSON_NOT_FOUND"));

        NewTranslationForUserTO newTranslationForUser = NewTranslationForUserTO.newBuilder()
                .withLanguagePairId(lesson.getLanguagePairId().toString())
                .withUserId(lesson.getUserId().toString())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder()
                        .withLanguageFrom(newWord.getLanguageFrom())
                        .withLanguageTO(newWord.getLanguageTO())
                        .build())
                .build();
        TranslationsForUserTO translationsForUserTO = createTranslation.forAllWords(newTranslationForUser);
        Set<ObjectId> translationids = translationsForUserTO.translations().stream().map(TranslationTO::id).map(ObjectId::new).collect(Collectors.toSet());
        lesson.addTranslations(translationids);

        return translationsForUserTO.translations().iterator().next();
    }
}

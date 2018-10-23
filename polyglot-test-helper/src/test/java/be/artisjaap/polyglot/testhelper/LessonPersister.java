package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class LessonPersister {

    @Autowired
    private LanguagePairPersister languagePairPersister;

    @Autowired
    private TranslationPracticePersister translationPracticePersister;

    @Autowired
    private LessonRepository lessonRepository;

    public Lesson randomLesson(int numberOfTranslations) {
        LanguagePair languagePair = languagePairPersister.randomLanguagePair();
        List<ObjectId> translations = IntStream.rangeClosed(1, numberOfTranslations)
                .boxed()
                .map(i -> translationPracticePersister.randomTranslationPracticeInKnowledgeStatus(languagePair, KnowledgeStatus.IN_PROGRESS))
                .map(TranslationPractice::getTranslationId)
                .collect(Collectors.toList());

        return lessonRepository.save(LessonMother.initRandom()
                .withUserId(languagePair.getUserId())
                .withLanguagePairId(languagePair.getId())
                .withTranslations(translations)
                .build());
    }

}

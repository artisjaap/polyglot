package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.assembler.LessonAssembler;
import be.artisjaap.polyglot.core.action.assembler.LessonTranslationPairAssembler;
import be.artisjaap.polyglot.core.action.assembler.NewLessonAssembler;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CreateLesson {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonAssembler lessonAssembler;

    @Autowired
    private LessonTranslationPairAssembler lessonTranslationPairAssembler;

    @Autowired
    private NewLessonAssembler newLessonAssembler;

    @Autowired
    private PracticeWords practiceWords;

    @Autowired
    private TranslationRepository translationRepository;


    public LessonTO create(NewLessonTO to){
        Lesson lesson = newLessonAssembler.assembleEntity(to);
        lessonRepository.save(lesson);
        return lessonAssembler.assembleTO(lesson);

    }


    public LessonTO automaticallyFor(NewAutomaticLessonTO newLesson) {
        List<Lesson> allLessons = lessonRepository.findByUserIdAndLanguagePairId(new ObjectId(newLesson.userId()), new ObjectId(newLesson.languagePairId()));
        List<ProgressStatus> status = Arrays.asList(ProgressStatus.IN_PROGRESS, ProgressStatus.KNOWN);
        List<TranslationPracticeTO> translationPracticeTOS = practiceWords.allPracticedWords(newLesson.userId(), newLesson.languagePairId(), status);


        Set<ObjectId> translations = autobuildTranslations(allLessons, translationPracticeTOS, newLesson);
        Lesson lesson = Lesson.newBuilder()
                .withName(newLesson.lessonTitle())
                .withUserId(new ObjectId(newLesson.userId()))
                .withLanguagePairId(new ObjectId(newLesson.languagePairId()))
                .withTranslations(translations)
                .build();
        lessonRepository.save(lesson);

        return lessonAssembler.assembleTO(lesson);

    }

    private Set<ObjectId> autobuildTranslations(List<Lesson> allLessons, List<TranslationPracticeTO> translationPracticeTOS, NewAutomaticLessonTO newLessonTO) {
        Map<ObjectId, Long> translationsUsedOrderedByOccurence = allLessons.stream()
                .flatMap(l -> l.getTranslations().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Set<ObjectId> wordsNeverUsed = translationPracticeTOS.stream()
                .map(TranslationPracticeTO::translationId)
                .map(ObjectId::new)
                .filter(to -> !translationsUsedOrderedByOccurence.containsKey(to))
                .limit(newLessonTO.maxNumberOfWords())
                .collect(Collectors.toSet());

        if (newLessonTO.maxNumberOfWords() > wordsNeverUsed.size()) {
            List<ObjectId> alreadyInLessonLeastUsed = translationsUsedOrderedByOccurence.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .limit(newLessonTO.maxNumberOfWords() - wordsNeverUsed.size())
                    .collect(Collectors.toList());
            wordsNeverUsed.addAll(alreadyInLessonLeastUsed);

        }

//        return StreamSupport.stream(translationPracticeRepository.findAllById(wordsNeverUsed).spliterator(), false).map(TranslationPractice::getTranslationId).collect(Collectors.toList());
        return wordsNeverUsed;
    }

    public LessonTranslationPairTO addTranslationToLesson(String lessonId, String translationId) {
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new UnsupportedOperationException(""));
        Translation translation = translationRepository.findById(new ObjectId(translationId)).orElseThrow(() -> new UnsupportedOperationException(""));
        lesson.addTranslation(translation.getId());
        lessonRepository.save(lesson);
        return lessonTranslationPairAssembler.assembleTO(translation);
    }

    public LessonTranslationPairTO removeTranslationFromLesson(String lessonId, String translationId) {
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new UnsupportedOperationException(""));
        Translation translation = translationRepository.findById(new ObjectId(translationId)).orElseThrow(() -> new UnsupportedOperationException(""));
        lesson.removeTranslation(translation.getId());
        lessonRepository.save(lesson);
        return lessonTranslationPairAssembler.assembleTO(translation);
    }
}

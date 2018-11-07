package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LessonAssembler implements Assembler<LessonTO, Lesson> {
    @Autowired
    private LessonTranslationPairAssembler lessonTranslationPairAssembler;

    @Autowired
    private TranslationRepository translationRepository;

    @Override
    public LessonTO assembleTO(Lesson lesson) {
        return assembleTO(lesson, true);
    }

    private LessonTO assembleTO(Lesson lesson, boolean includeSolution) {
        Function<Translation, LessonTranslationPairTO> assembleTO = includeSolution?lessonTranslationPairAssembler::assembleTO:lessonTranslationPairAssembler::assembleTOWithoutSolution;


        List<LessonTranslationPairTO> translationPairTOS = StreamSupport.stream(translationRepository.findAllById(lesson.getTranslations()).spliterator(), false)
                .map(assembleTO)
                .collect(Collectors.toList());

        if(lesson.getTranslations().size() != translationPairTOS.size()){
            throw new IllegalStateException("translations missing");
        }

        return LessonTO.newBuilder()
                .forDocument(lesson)
                .withName(lesson.getName())
                .withLanguagePairId(lesson.getLanguagePairId().toString())
                .withTranslations(translationPairTOS)
                .withUserId(lesson.getUserId().toString())
                .build();
    }

    public LessonTO assembleTOwithoutSolution(Lesson lesson){
        return assembleTO(lesson, false);
    }



    @Override
    public Lesson assembleEntity(LessonTO lessonTO) {
        return Lesson.newBuilder()
                .forDocument(lessonTO)
                .withName(lessonTO.name())
                .withLanguagePairId(new ObjectId(lessonTO.languagePairId()))
                .withTranslations(lessonTO.translations().stream()
                        .map(LessonTranslationPairTO::translationId)
                        .map(ObjectId::new).collect(Collectors.toSet()))
                .withUserId(new ObjectId(lessonTO.id()))
                .build();
    }
}

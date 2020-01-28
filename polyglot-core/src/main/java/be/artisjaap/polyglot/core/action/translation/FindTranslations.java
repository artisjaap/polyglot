package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FindTranslations {

    @Resource
    private TranslationRepository translationRepository;

    @Resource
    private TranslationAssembler translationAssembler;

    @Resource
    private LessonRepository lessonRepository;


    public Optional<TranslationTO> byId(String translationId) {
        return translationRepository.findById(new ObjectId(translationId)).map(translationAssembler::assembleTO);
    }

    public List<TranslationTO> containing(String languagePairId, List<String> languageA) {
        List<Translation> translations = translationRepository.findByLanguagePairIdAndLanguageAIn(new ObjectId(languagePairId), languageA);
        return translations.stream().map(translationAssembler::assembleTO).collect(Collectors.toList());
    }

    public List<TranslationTO> allWordsForLanguagePair(String languagePairId){
        List<Translation> translations = translationRepository.findByLanguagePairId(new ObjectId(languagePairId));
        return translations.stream().map(translationAssembler::assembleTO).collect(Collectors.toList());
    }

    public List<TranslationTO> allWordsForLesson(String lessonId){
        Lesson lesson = lessonRepository.findById(new ObjectId(lessonId)).orElseThrow(() -> new ValidationException("lesson with id not found"));
        return StreamSupport.stream(translationRepository.findAllById(lesson.getTranslations()).spliterator(), true)
                .map(translationAssembler::assembleTO)
                .collect(Collectors.toList());
    }

    public List<TranslationTO> latestFor(String languagePairId, int count){
        return new ArrayList<>();
    }

}

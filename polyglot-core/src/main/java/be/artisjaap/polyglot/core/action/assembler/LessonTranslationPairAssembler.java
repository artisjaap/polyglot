package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.common.utils.ListUtils;
import be.artisjaap.common.validation.ValidationException;
import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import be.artisjaap.polyglot.core.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LessonTranslationPairAssembler implements Assembler<LessonTranslationPairTO, Translation> {
    @Autowired
    private LanguagePairRepository languagePairRepository;

    @Override
    public LessonTranslationPairTO assembleTO(Translation doc) {
        return assembleTO(doc, true);
    }

    private LessonTranslationPairTO assembleTO(Translation translation, boolean includeSolution) {
        LanguagePair languagePair = languagePairRepository.findById(translation.getLanguagePairId()).orElseThrow(() -> new ValidationException("NOT FOUND"));

        return LessonTranslationPairTO.newBuilder()
                .withLanguageFrom(languagePair.getLanguageFrom())
                .withLanguageTo(languagePair.getLanguageTo())
                .withIsReverse(false)
                .withQuestion(ListUtils.getRandomFromCollection(translation.getLanguageA()))
                .withQuestions(translation.getLanguageA())
                .withSolutions(includeSolution?translation.getLanguageB(): Set.of())
                .withTranslationId(translation.getId().toString())
                .build();
    }

    @Override
    public Translation assembleEntity(LessonTranslationPairTO lessonTranslationPairTO) {
        throw new UnsupportedOperationException("");
    }

    public LessonTranslationPairTO assembleTOWithoutSolution(Translation translation) {
        return assembleTO(translation, false);
    }




}

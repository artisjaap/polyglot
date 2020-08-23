package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LessonTranslationPairResponseMapper implements ResponseMapper<LessonTranslationPairTO, TranslationResponse> {

    @Override
    public TranslationResponse map(LessonTranslationPairTO lessonTranslationPairTO) {
        return TranslationResponse.builder()
                .id(lessonTranslationPairTO.translationId())
                .languageA(Set.of(lessonTranslationPairTO.question()))
                .languageB(lessonTranslationPairTO.solutions())
                .build();
    }
}

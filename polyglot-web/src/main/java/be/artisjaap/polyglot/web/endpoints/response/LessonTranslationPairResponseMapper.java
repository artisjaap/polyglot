package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTranslationPairTO;
import org.springframework.stereotype.Component;

@Component
public class LessonTranslationPairResponseMapper implements ResponseMapper<LessonTranslationPairTO, TranslationResponse> {

    @Override
    public TranslationResponse map(LessonTranslationPairTO lessonTranslationPairTO) {
        return TranslationResponse.builder()
                .id(lessonTranslationPairTO.translationId())
                .languageA(lessonTranslationPairTO.question())
                .languageB(lessonTranslationPairTO.solution())
                .build();
    }
}

package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationTO;
import org.springframework.stereotype.Component;

@Component
public class TranslationForLessonResponseMapper {

    public TranslationForLessonResponse map(TranslationTO to){
        return TranslationForLessonResponse.builder()
                .id(to.id())
                .languageA(to.languageA())
                .languageB(to.languageB())
                .build();
    }

    public TranslationForLessonResponse map(TranslationTO to, String lessonId){
        return TranslationForLessonResponse.builder()
                .id(to.id())
                .lessonId(lessonId)
                .languageA(to.languageA())
                .languageB(to.languageB())
                .build();
    }
}

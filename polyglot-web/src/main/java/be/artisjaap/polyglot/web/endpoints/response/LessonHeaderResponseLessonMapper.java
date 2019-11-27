package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import org.springframework.stereotype.Component;

@Component
public class LessonHeaderResponseLessonMapper implements ResponseMapper<LessonTO, LessonHeaderResponse> {
    @Override
    public LessonHeaderResponse map(LessonTO lessonTO) {
        return LessonHeaderResponse.builder()
                .name(lessonTO.name())
                .id(lessonTO.id())
                .build();
    }
}

package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LessonResponseMapper implements ResponseMapper<LessonTO, LessonResponse> {
    @Resource
    private TranslationResponseMapper translationResponseMapper;

    @Override
    public LessonResponse map(LessonTO lessonTO) {
        return LessonResponse.builder()
                .id(lessonTO.id())
                .title(lessonTO.name())
                .translations(translationResponseMapper.mapToResponse(lessonTO.translations()))
                .build();
    }
}

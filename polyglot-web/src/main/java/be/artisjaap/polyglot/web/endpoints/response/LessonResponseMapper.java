package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LessonResponseMapper implements ResponseMapper<LessonTO, LessonResponse> {
    @Resource
    private LessonTranslationPairResponseMapper lessonTranslationPairResponseMapper;

    @Override
    public LessonResponse map(LessonTO lessonTO) {
        return LessonResponse.builder()
                .id(lessonTO.id())
                .title(lessonTO.name())
                .languagePairId(lessonTO.languagePairId())
                .translations(lessonTranslationPairResponseMapper.mapToResponse(lessonTO.translations()))
                .build();
    }
}

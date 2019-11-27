package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;
import org.springframework.stereotype.Component;

@Component
public class LessonHeaderResponseMapper implements ResponseMapper<LessonHeaderTO, LessonHeaderResponse>{

    public LessonHeaderResponse map(LessonHeaderTO to){
        return LessonHeaderResponse.builder()
                .id(to.id())
                .name(to.name())
                .build();
    }
}

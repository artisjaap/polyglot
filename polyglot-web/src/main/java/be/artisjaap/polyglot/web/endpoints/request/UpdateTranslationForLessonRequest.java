package be.artisjaap.polyglot.web.endpoints.request;


import lombok.*;

import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTranslationForLessonRequest {
    private String lessonId;
    private String translationId;
    private Set<String> languageA;
    private Set<String> languageB;

    public boolean translationIsForLesson(){
        return Objects.nonNull(lessonId);
    }

}

package be.artisjaap.polyglot.web.endpoints.request;


import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTranslationForLessonRequest {
    private String languagePairId;
    private String lessonId;
    private String languageA;
    private String languageB;

    public boolean translationIsForLesson(){
        return Objects.nonNull(lessonId);
    }
}

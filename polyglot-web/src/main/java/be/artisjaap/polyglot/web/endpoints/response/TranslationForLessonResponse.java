package be.artisjaap.polyglot.web.endpoints.response;


import be.artisjaap.i18n.action.to.TranslationTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationForLessonResponse {
    @NonNull
    private String id;
    private String lessonId;
    @NonNull
    private String languageA;
    @NonNull
    private String languageB;

}

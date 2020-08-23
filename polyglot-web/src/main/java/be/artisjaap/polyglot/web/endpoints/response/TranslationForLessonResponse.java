package be.artisjaap.polyglot.web.endpoints.response;


import be.artisjaap.i18n.action.to.TranslationTO;
import lombok.*;

import java.util.Set;

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
    private Set<String> languageA;
    @NonNull
    private Set<String> languageB;

}

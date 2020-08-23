package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@Builder
@Getter
public class NewTranslationInLessonTO {
    @NonNull
    private String lessonId;
    @NonNull
    private Set<String> languageFrom;
    @NonNull
    private Set<String> languageTO;
}

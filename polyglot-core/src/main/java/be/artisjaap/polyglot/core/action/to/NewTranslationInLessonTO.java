package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class NewTranslationInLessonTO {
    @NonNull
    private String lessonId;
    @NonNull
    private String languageFrom;
    @NonNull
    private String languageTO;
}

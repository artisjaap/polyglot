package be.artisjaap.polyglot.core.action.to.lessonpractice;

import be.artisjaap.polyglot.core.model.ProgressStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LessonPracticeTranslationStatusTO {

    private String languageA;
    private String languageB;
    private int asked;
    private int correct;
    private int percentage;
    private ProgressStatus status;
    
}

package be.artisjaap.polyglot.core.action.to.lessonpractice;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class LessonPracticeStatusTO {

    private List<LessonPracticeTranslationStatusTO> translationStatusses;
}

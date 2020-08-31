package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeTranslationStatusTO;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LessonPracticeTranslationStatusResponse {
    private String languageA;
    private String languageB;
    private int asked;
    private int correct;
    private int percentage;
    private ProgressStatus status;

    public static LessonPracticeTranslationStatusResponse from(LessonPracticeTranslationStatusTO lessonPracticeTranslationStatusTO) {

        return LessonPracticeTranslationStatusResponse.builder()
                .asked(lessonPracticeTranslationStatusTO.getAsked())
                .correct(lessonPracticeTranslationStatusTO.getCorrect())
                .percentage(lessonPracticeTranslationStatusTO.getPercentage())
                .status(lessonPracticeTranslationStatusTO.getStatus())
                .languageA(lessonPracticeTranslationStatusTO.getLanguageA())
                .languageB(lessonPracticeTranslationStatusTO.getLanguageB())
                .build();
    }
}

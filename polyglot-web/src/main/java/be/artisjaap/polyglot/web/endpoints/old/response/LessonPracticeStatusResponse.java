package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeStatusTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class LessonPracticeStatusResponse {


    private List<LessonPracticeTranslationStatusResponse> translationstatusses;
    
    public static LessonPracticeStatusResponse from(LessonPracticeStatusTO lessonPracticeStatus) {

        return LessonPracticeStatusResponse.builder()
                .translationstatusses(lessonPracticeStatus.getTranslationStatusses()
                        .stream()
                        .map(LessonPracticeTranslationStatusResponse::from)
                        .collect(Collectors.toList()))       
                .build();
    }
}

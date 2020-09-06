package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.to.LessonTO;
import org.springframework.stereotype.Component;


import java.nio.charset.Charset;
import java.util.stream.Collectors;

@Component
public class CreateFileForLesson {

    private final FindLesson findLesson;

    public CreateFileForLesson(final FindLesson findLesson){
        this.findLesson = findLesson;
    }

    public byte[] forLessonId(String lessonId){
        LessonTO lessonTO = findLesson.forPracticing(lessonId);

        StringBuffer sb = new StringBuffer();
        sb.append("@Lesson=").append(lessonTO.name()).append("\n");

        lessonTO.translations().forEach(translation -> {
            sb.append(translation.questions().stream().collect(Collectors.joining("|")));
            sb.append("=");
            sb.append(translation.solutions().stream().collect(Collectors.joining("|")));
            sb.append("\n");

        });

        return sb.toString().getBytes(Charset.forName("UTF-8"));
    }
}

package be.artisjaap.polyglot.core.action.translationloader;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Component
public class FileLoaderTag {




    public static TranslationTag of(String line){
        return calcTagName(line);
    }

    private static TranslationTag calcTagName(String line) {
        if (line.startsWith("@Lesson")) {
            return TagLesson.of(line);
        }

        else if(line.startsWith("@Verb")){
            return TagVerb.of(line);
        }
        else if(line.startsWith("@Tense")){
            return TagTense.of(line);
        }
        else if(line.startsWith("@Tags")){
            return TagTag.of(line);
        }

        throw new IllegalStateException("Unknown Tag on line: " + line);

    }


}

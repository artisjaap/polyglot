package be.artisjaap.polyglot.core.action.translationloader;

import be.artisjaap.polyglot.core.model.verbs.TimeOfVerb;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagLesson implements TranslationTag<ParsedLesson> {
    private Pattern pattern = Pattern.compile("@Lesson=(.*)");
    private String rawData;

    private TagLesson(String rawString){
        this.rawData = rawString;
    }

    public static TagLesson of(String rawData){
        return new TagLesson(rawData);
    }

    @Override
    public TagName tagName() {
        return TagName.LESSON;
    }

    @Override
    public ParsedLesson parsedTag() {
        Matcher matcher = pattern.matcher(rawData);
        if(matcher.find()){
            return ParsedLesson.builder()
                    .lessonName(matcher.group(1))
                    .build();
        }
        return null;
    }
}

@Builder
@Getter
class ParsedLesson {
    private String lessonName;


}

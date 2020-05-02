package be.artisjaap.polyglot.core.action.translationloader;


import be.artisjaap.polyglot.core.model.verbs.TimeOfVerb;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagTense implements TranslationTag<ParsedTense> {
    private Pattern pattern = Pattern.compile("@Tense=(.*)");
    private String rawData;

    private TagTense(String rawString){
        this.rawData = rawString;
    }

    public static TagTense of(String rawData){
        return new TagTense(rawData);
    }

    @Override
    public TagName tagName() {
        return TagName.TENSE;
    }

    @Override
    public ParsedTense parsedTag() {
        Matcher matcher = pattern.matcher(rawData);
        if(matcher.find()){
            return ParsedTense.builder()
                    .timeOfVerb(TimeOfVerb.valueOf(matcher.group(1).toUpperCase()))
                    .build();
        }
        return null;
    }
}

@Builder
@Getter
class ParsedTense {
    private TimeOfVerb timeOfVerb;


}

package be.artisjaap.polyglot.core.action.translationloader;

import be.artisjaap.polyglot.core.model.verbs.TimeOfVerb;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TagTag implements TranslationTag<List<ParsedTag>> {
    private Pattern pattern = Pattern.compile("@Tags=(.*)");
    private String rawData;

    private TagTag(String rawString){
        this.rawData = rawString;
    }

    public static TagTag of(String rawData){
        return new TagTag(rawData);
    }

    @Override
    public TagName tagName() {
        return TagName.TAG;
    }

    @Override
    public List<ParsedTag> parsedTag() {
        Matcher matcher = pattern.matcher(rawData);
        if(matcher.find()){
            return List.of(matcher.group(1).split(","))
                    .stream()
                    .map(tag -> ParsedTag.builder().tag(tag).build())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}

@Builder
@Getter
class ParsedTag {
    private String tag;

}

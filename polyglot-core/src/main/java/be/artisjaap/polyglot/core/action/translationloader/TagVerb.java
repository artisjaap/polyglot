package be.artisjaap.polyglot.core.action.translationloader;


import lombok.Builder;
import lombok.Getter;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TagVerb implements TranslationTag<ParsedVerb> {
    private Pattern pattern = Pattern.compile("@Verb\\(([^,]*),(.*)\\)");
    private String rawData;

    private TagVerb(String rawString){
        this.rawData = rawString;
    }

    public static TagVerb of(String rawData){
        return new TagVerb(rawData);
    }

    @Override
    public TagName tagName() {
        return TagName.VERB;
    }

    @Override
    public ParsedVerb parsedTag() {
        Matcher matcher = pattern.matcher(rawData);
        if(matcher.find()){
            return ParsedVerb.builder()
                    .infiniteLanguageA(matcher.group(1))
                    .infiniteLanguageB(matcher.group(2))
                    .build();
        }
        return null;
    }
}

@Builder
@Getter
class ParsedVerb {
    private String infiniteLanguageA;
    private String infiniteLanguageB;

}

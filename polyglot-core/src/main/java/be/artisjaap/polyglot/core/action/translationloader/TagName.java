package be.artisjaap.polyglot.core.action.translationloader;

import java.util.regex.Pattern;

public enum TagName  {
    LESSON(Pattern.compile("@Lesson=(.*)")),
    VERB(Pattern.compile("@Verb\\(([^,])(.*)\\)")),
    TENSE(Pattern.compile("@Tense=(.*)")),
    TAG(Pattern.compile("@Tense=(.*)"));

    private Pattern tagPattern;

    TagName(Pattern pattern){
        this.tagPattern = pattern;
    }


}

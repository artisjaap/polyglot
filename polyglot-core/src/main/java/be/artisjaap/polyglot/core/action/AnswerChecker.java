package be.artisjaap.polyglot.core.action;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AnswerChecker {

    //a string answer, optionally followd by [...]
    //that can contain extra information not part of the answer
    private Pattern questionStripe = Pattern.compile("^([^\\[]*)(\\[.*\\])?$");

    public boolean checkFor(String expectedAnswer, String givenAnswer){

        Matcher matcher = questionStripe.matcher(expectedAnswer);
        String striped = matcher.find() ? matcher.group(1) : expectedAnswer;
        return striped.trim().equalsIgnoreCase(givenAnswer.trim());
    }
}

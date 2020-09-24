package be.artisjaap.polyglot.core.action.lesson;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AnswerChecker {

    //a string answer, optionally followd by [...]
    //that can contain extra information not part of the answer
    private Pattern questionStripe = Pattern.compile("^([^\\[]*)(\\[.*\\])?$");

    public boolean checkFor(Set<String> expectedAnswers, String givenAnswer, Boolean normalized){

        return expectedAnswers.stream().filter(expectedAnswer -> {
            Matcher matcher = questionStripe.matcher(expectedAnswer);
            String striped = matcher.find() ? matcher.group(1) : expectedAnswer;
            String expectedAnswerCleaned = normalized?stripAccents(striped.trim()):striped.trim();
            String givenAnswerCleaned = normalized?stripAccents(givenAnswer.trim()):givenAnswer.trim();
            return expectedAnswerCleaned.equalsIgnoreCase(givenAnswerCleaned);
        }).findFirst().isPresent();
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}

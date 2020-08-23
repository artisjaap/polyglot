package be.artisjaap.polyglot.core.action.to.mistakes;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Set;

@Builder
@Data
public class LanguageMistakeDetailTO {
    private String translationId;
    private String question;
    private Set<String> correctAnswer;
    private Set<String> givenAnswers;
    private int timesCorrect;
    private int timesIncorrect;
    private int total;
}

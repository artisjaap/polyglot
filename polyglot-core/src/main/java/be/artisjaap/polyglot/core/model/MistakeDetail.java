package be.artisjaap.polyglot.core.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Set;

@Builder
@Data
public class MistakeDetail {
    private ObjectId translationId;
    private String question;
    private Set<String> correctAnswer;
    private Set<String> givenAnswers;
    private int timesCorrect;
    private int timesIncorrect;

    public int total() {
        return timesCorrect + timesIncorrect;
    }
}

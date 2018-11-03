package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.PracticeHealthTO;
import be.artisjaap.polyglot.core.model.PracticeHealth;
import org.springframework.stereotype.Component;

@Component
public class PracticeHealthAssembler implements Assembler<PracticeHealthTO, PracticeHealth> {
    @Override
    public PracticeHealthTO assembleTO(PracticeHealth doc) {
        return PracticeHealthTO.newBuilder()
                .withCorrectAnswerd(doc.getCorrectAnswered())
                .withDifficulty(doc.getDifficulty())
                .withHealthPercentage(doc.healthPercentage())
                .withHealthy(doc.healthy())
                .withIncorrectlyAnswerd(doc.getIncorrectlyAnswered())
                .withNewWordsAdded(doc.getNewWordsAdded())
                .build();
    }

    @Override
    public PracticeHealth assembleEntity(PracticeHealthTO practiceHealthTO) {
        return PracticeHealth.newBuilder()
                .withCorrectAnswered(practiceHealthTO.correctAnswerd())
                .withDifficulty(practiceHealthTO.correctAnswerd())
                .withIncorrectlyAnswered(practiceHealthTO.incorrectlyAnswerd())
                .withNewWordsAdded(practiceHealthTO.newWordsAdded())
                .build();
    }
}

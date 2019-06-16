package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.PracticeHealth;

public class PracticeHealthMother {

    public static PracticeHealth.Builder initRandom(){
        return PracticeHealth.newBuilder()
                .withNewWordsAdded(0)
                .withDifficulty(0)
                .withCorrectAnswered(0)
                .withIncorrectlyAnswered(0);
    }
}

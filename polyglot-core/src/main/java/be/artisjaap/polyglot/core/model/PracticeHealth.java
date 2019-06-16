package be.artisjaap.polyglot.core.model;

public class PracticeHealth {

    private Integer correctAnswered;
    private Integer incorrectlyAnswered;
    private Integer newWordsAdded;
    private Integer difficulty;

    private PracticeHealth(){}

    public Integer healthPercentage() {
        int healthRate = (incorrectlyAnswered + newWordsAdded) * 5;
        if(healthRate == 0) {
            return 0;
        }else{
            return Math.round((1f * correctAnswered / healthRate) * 100);
        }
    }

    private PracticeHealth(Builder builder) {
        correctAnswered = builder.correctAnswered;
        incorrectlyAnswered = builder.incorrectlyAnswered;
        newWordsAdded = builder.newWordsAdded;
        difficulty = builder.difficulty;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public Integer getCorrectAnswered() {
        return correctAnswered;
    }

    public Integer getIncorrectlyAnswered() {
        return incorrectlyAnswered;
    }

    public Integer getNewWordsAdded() {
        return newWordsAdded;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public Boolean healthy() {
        return healthPercentage() > 80;
    }

    public void addNewWord() {
        newWordsAdded++;
    }

    public void addCorrect() {
        correctAnswered++;
    }

    public void addIncorrect() {
        incorrectlyAnswered++;
    }

    public static final class Builder {
        private Integer correctAnswered = 0;
        private Integer incorrectlyAnswered = 0;
        private Integer newWordsAdded = 0;
        private Integer difficulty = 5;

        private Builder() {
        }

        public Builder withCorrectAnswered(Integer val) {
            correctAnswered = val;
            return this;
        }

        public Builder withIncorrectlyAnswered(Integer val) {
            incorrectlyAnswered = val;
            return this;
        }

        public Builder withNewWordsAdded(Integer val) {
            newWordsAdded = val;
            return this;
        }

        public Builder withDifficulty(Integer val) {
            difficulty = val;
            return this;
        }

        public PracticeHealth build() {
            return new PracticeHealth(this);
        }
    }
}

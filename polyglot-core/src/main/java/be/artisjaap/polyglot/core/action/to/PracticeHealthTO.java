package be.artisjaap.polyglot.core.action.to;

public class PracticeHealthTO {
    private Integer healthPercentage;
    private Boolean healthy;
    private Integer correctAnswerd;
    private Integer incorrectlyAnswerd;
    private Integer newWordsAdded;
    private Integer difficulty;

    private PracticeHealthTO(Builder builder) {
        healthPercentage = builder.healthPercentage;
        healthy = builder.healthy;
        correctAnswerd = builder.correctAnswerd;
        incorrectlyAnswerd = builder.incorrectlyAnswerd;
        newWordsAdded = builder.newWordsAdded;
        difficulty = builder.difficulty;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer healthPercentage() {
        return healthPercentage;
    }

    public Boolean healthy() {
        return healthy;
    }

    public Integer correctAnswerd() {
        return correctAnswerd;
    }

    public Integer incorrectlyAnswerd() {
        return incorrectlyAnswerd;
    }

    public Integer newWordsAdded() {
        return newWordsAdded;
    }

    public Integer difficulty() {
        return difficulty;
    }

    public static final class Builder {
        private Integer healthPercentage;
        private Boolean healthy;
        private Integer correctAnswerd;
        private Integer incorrectlyAnswerd;
        private Integer newWordsAdded;
        private Integer difficulty;

        private Builder() {
        }

        public Builder withHealthPercentage(Integer val) {
            healthPercentage = val;
            return this;
        }

        public Builder withHealthy(Boolean val) {
            healthy = val;
            return this;
        }

        public Builder withCorrectAnswerd(Integer val) {
            correctAnswerd = val;
            return this;
        }

        public Builder withIncorrectlyAnswerd(Integer val) {
            incorrectlyAnswerd = val;
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

        public PracticeHealthTO build() {
            return new PracticeHealthTO(this);
        }
    }
}

package be.artisjaap.polyglot.core.model;

public class UserSettings {

    private Integer initialNumberOnPracticeWords;
    private Integer newWordEveryXexcersises;
    private Integer flagAsKnowWhenWinningStreakHitsX;

    private UserSettings(){}

    private UserSettings(Builder builder) {
        initialNumberOnPracticeWords = builder.initialNumberOnPracticeWords;
        newWordEveryXexcersises = builder.newWordEveryXexcersises;
        flagAsKnowWhenWinningStreakHitsX = builder.flagAsKnowWhenWinningStreakHitsX;
    }

    public static UserSettings buildDefaults() {
        return newBuilder()
                .withFlagAsKnowWhenWinningStreakHitsX(5)
                .withInitialNumberOnPracticeWords(5)
                .withNewWordEveryXexcersises(20)
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getInitialNumberOnPracticeWords() {
        return initialNumberOnPracticeWords;
    }

    public Integer getNewWordEveryXexcersises() {
        return newWordEveryXexcersises;
    }

    public Integer getFlagAsKnowWhenWinningStreakHitsX() {
        return flagAsKnowWhenWinningStreakHitsX;
    }

    public void setInitialNumberOnPracticeWords(Integer initialNumberOnPracticeWords) {
        this.initialNumberOnPracticeWords = initialNumberOnPracticeWords;
    }

    public void setNewWordEveryXexcersises(Integer newWordEveryXexcersises) {
        this.newWordEveryXexcersises = newWordEveryXexcersises;
    }

    public void setFlagAsKnowWhenWinningStreakHitsX(Integer flagAsKnowWhenWinningStreakHitsX) {
        this.flagAsKnowWhenWinningStreakHitsX = flagAsKnowWhenWinningStreakHitsX;
    }

    public static final class Builder {
        private Integer initialNumberOnPracticeWords;
        private Integer newWordEveryXexcersises;
        private Integer flagAsKnowWhenWinningStreakHitsX;

        private Builder() {
        }

        public Builder withInitialNumberOnPracticeWords(Integer initialNumberOnPracticeWords) {
            this.initialNumberOnPracticeWords = initialNumberOnPracticeWords;
            return this;
        }

        public Builder withNewWordEveryXexcersises(Integer newWordEveryXexcersises) {
            this.newWordEveryXexcersises = newWordEveryXexcersises;
            return this;
        }

        public Builder withFlagAsKnowWhenWinningStreakHitsX(Integer flagAsKnowWhenWinningStreakHitsX) {
            this.flagAsKnowWhenWinningStreakHitsX = flagAsKnowWhenWinningStreakHitsX;
            return this;
        }

        public UserSettings build() {
            return new UserSettings(this);
        }
    }
}

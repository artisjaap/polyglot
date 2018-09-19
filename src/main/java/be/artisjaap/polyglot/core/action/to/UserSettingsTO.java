package be.artisjaap.polyglot.core.action.to;

public class UserSettingsTO {
    private Integer initialNumberOnPracticeWords;
    private Integer newWordEveryXexcersises;
    private Integer flagAsKnowWhenWinningStreakHitsX;



    private UserSettingsTO(Builder builder) {
        initialNumberOnPracticeWords = builder.initialNumberOnPracticeWords;
        newWordEveryXexcersises = builder.newWordEveryXexcersises;
        flagAsKnowWhenWinningStreakHitsX = builder.flagAsKnowWhenWinningStreakHitsX;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer initialNumberOnPracticeWords() {
        return initialNumberOnPracticeWords;
    }

    public Integer newWordEveryXexcersises() {
        return newWordEveryXexcersises;
    }

    public Integer flagAsKnowWhenWinningStreakHitsX() {
        return flagAsKnowWhenWinningStreakHitsX;
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

        public UserSettingsTO build() {
            return new UserSettingsTO(this);
        }
    }
}

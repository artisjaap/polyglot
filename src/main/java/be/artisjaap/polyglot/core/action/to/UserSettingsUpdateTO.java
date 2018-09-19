package be.artisjaap.polyglot.core.action.to;

public class UserSettingsUpdateTO {
    private String userId;
    private Integer initialNumberOnPracticeWords;
    private Integer newWordEveryXexcersises;
    private Integer flagAsKnowWhenWinningStreakHitsX;

    private UserSettingsUpdateTO(Builder builder) {
        userId = builder.userId;
        initialNumberOnPracticeWords = builder.initialNumberOnPracticeWords;
        newWordEveryXexcersises = builder.newWordEveryXexcersises;
        flagAsKnowWhenWinningStreakHitsX = builder.flagAsKnowWhenWinningStreakHitsX;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
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
        private String userId;
        private Integer initialNumberOnPracticeWords;
        private Integer newWordEveryXexcersises;
        private Integer flagAsKnowWhenWinningStreakHitsX;

        private Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
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

        public UserSettingsUpdateTO build() {
            return new UserSettingsUpdateTO(this);
        }
    }
}

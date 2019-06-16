package be.artisjaap.polyglot.core.action.to;

public class JournalStatisticsTO {
    private Integer numerOfQuestions;
    private Integer correctlyAnswered;
    private Integer incorrectlyAnswered;
    private Float percentageCorrect;

    private JournalStatisticsTO(Builder builder) {
        numerOfQuestions = builder.numerOfQuestions;
        correctlyAnswered = builder.correctlyAnswered;
        incorrectlyAnswered = builder.incorrectlyAnswered;
        percentageCorrect = builder.percentageCorrect;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer numerOfQuestions() {
        return numerOfQuestions;
    }

    public Integer correctlyAnswered() {
        return correctlyAnswered;
    }

    public Integer incorrectlyAnswered() {
        return incorrectlyAnswered;
    }

    public Float percentageCorrect() {
        return percentageCorrect;
    }

    public static final class Builder {
        private Integer numerOfQuestions;
        private Integer correctlyAnswered;
        private Integer incorrectlyAnswered;
        private Float percentageCorrect;

        private Builder() {
        }

        public Builder withNumerOfQuestions(Integer val) {
            numerOfQuestions = val;
            return this;
        }

        public Builder withCorrectlyAnswered(Integer val) {
            correctlyAnswered = val;
            return this;
        }

        public Builder withIncorrectlyAnswered(Integer val) {
            incorrectlyAnswered = val;
            return this;
        }

        public Builder withPercentageCorrect(Float val) {
            percentageCorrect = val;
            return this;
        }

        public JournalStatisticsTO build() {
            return new JournalStatisticsTO(this);
        }
    }
}

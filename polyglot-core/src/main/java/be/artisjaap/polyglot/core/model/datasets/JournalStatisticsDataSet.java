package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.JournalStatisticsTO;

public class JournalStatisticsDataSet {
    private String numberOfQuestions;
    private String correctlyAnswered;
    private String incorrectlyAnswered;
    private String percentageCorrect;

    private JournalStatisticsDataSet(Builder builder) {
        numberOfQuestions = builder.numerOfQuestions;
        correctlyAnswered = builder.correctlyAnswered;
        incorrectlyAnswered = builder.incorrectlyAnswered;
        percentageCorrect = builder.percentageCorrect;
    }

    public static JournalStatisticsDataSet from(JournalStatisticsTO journalStatisticsTO) {
        return JournalStatisticsDataSet.newBuilder()
                .withCorrectlyAnswered(journalStatisticsTO.correctlyAnswered().toString())
                .withIncorrectlyAnswered(journalStatisticsTO.incorrectlyAnswered().toString())
                .withNumerOfQuestions(journalStatisticsTO.numerOfQuestions().toString())
                .withPercentageCorrect(String.format("%.2f", journalStatisticsTO.percentageCorrect()))
                .build();
    }

    public static JournalStatisticsDataSet dummy() {
        return JournalStatisticsDataSet.newBuilder()
                .withPercentageCorrect("82.85")
                .withNumerOfQuestions("70")
                .withIncorrectlyAnswered("12")
                .withCorrectlyAnswered("58")
                .build();
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public String getIncorrectlyAnswered() {
        return incorrectlyAnswered;
    }

    public String getPercentageCorrect() {
        return percentageCorrect;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String numerOfQuestions;
        private String correctlyAnswered;
        private String incorrectlyAnswered;
        private String percentageCorrect;

        private Builder() {
        }

        public Builder withNumerOfQuestions(String val) {
            numerOfQuestions = val;
            return this;
        }

        public Builder withCorrectlyAnswered(String val) {
            correctlyAnswered = val;
            return this;
        }

        public Builder withIncorrectlyAnswered(String val) {
            incorrectlyAnswered = val;
            return this;
        }

        public Builder withPercentageCorrect(String val) {
            percentageCorrect = val;
            return this;
        }

        public JournalStatisticsDataSet build() {
            return new JournalStatisticsDataSet(this);
        }
    }
}

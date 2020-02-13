package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.model.ReferenceableTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LanguagePracticeJournalTO extends ReferenceableTO {

    private String userId;
    private String languagePairId;
    private LocalDate from;
    private LocalDate until;
    private List<TranslationJournalTO> translationJournalList;

    private LanguagePracticeJournalTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        from = builder.from;
        until = builder.until;
        translationJournalList = builder.translationJournalList;
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(LanguagePracticeJournalTO copy) {
        Builder builder = new Builder();
        builder.userId = copy.userId;
        builder.languagePairId = copy.languagePairId;
        builder.from = copy.from;
        builder.until = copy.until;
        builder.translationJournalList = copy.translationJournalList;
        return builder;
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public LocalDate from() {
        return from;
    }

    public LocalDate until() {
        return until;
    }

    public List<TranslationJournalTO> translationJournalList() {
        return translationJournalList;
    }

    public JournalStatisticsTO calculateStatistics() {
        int total = totalAnswers();
        int correct = correctAnswers();
        int incorrect = total - correct;
        float percentage = correct / total;

        return JournalStatisticsTO.newBuilder()
                .withCorrectlyAnswered(correct)
                .withIncorrectlyAnswered(incorrect)
                .withNumerOfQuestions(total)
                .withPercentageCorrect(percentage)
                .build();
    }

    private int totalAnswers() {
        return translationJournalList.size();
    }

    private int correctAnswers() {
        return Long.valueOf(translationJournalList.stream().filter(TranslationJournalTO::correct).count()).intValue();
    }



    public static final class Builder extends AbstractBuilder<Builder> {
        private String userId;
        private String languagePairId;
        private LocalDate from;
        private LocalDate until;
        private List<TranslationJournalTO> translationJournalList = new ArrayList<>();

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withFrom(LocalDate val) {
            from = val;
            return this;
        }

        public Builder withUntil(LocalDate val) {
            until = val;
            return this;
        }

        public Builder withTranslationJournalList(List<TranslationJournalTO> val) {
            translationJournalList = val;
            return this;
        }

        public LanguagePracticeJournalTO build() {
            return new LanguagePracticeJournalTO(this);
        }
    }
}

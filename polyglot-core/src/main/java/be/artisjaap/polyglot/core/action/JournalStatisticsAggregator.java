package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.to.JournalStatisticsTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import org.springframework.stereotype.Component;

@Component
public class JournalStatisticsAggregator {

    public JournalStatisticsTO forResult(LanguagePracticeJournalTO result){
        Integer totalNumberOfQuestions = numberOfQuestions(result);
        int correctQuestions = correctAnswered(result).intValue();
        return JournalStatisticsTO.newBuilder()
                .withCorrectlyAnswered(correctQuestions)
                .withNumerOfQuestions(totalNumberOfQuestions)
                .withIncorrectlyAnswered(totalNumberOfQuestions-correctQuestions)
                .withPercentageCorrect(correctQuestions*100.0f/totalNumberOfQuestions)
                .build();
    }

    private Long correctAnswered(LanguagePracticeJournalTO result) {
        return result.translationJournalList().stream()
                .filter(TranslationJournalTO::correct)
                .count();
    }

    private Integer numberOfQuestions(LanguagePracticeJournalTO result){
        return result.translationJournalList().size();
    }
}

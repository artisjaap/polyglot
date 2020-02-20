package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.action.to.mistakes.LanguageMistakeDetailTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@Data
public class MistakeDataSet {
    private String translationId;
    private String question;
    private String correctAnswer;
    private Set<String> givenAnswers;
    private int timesCorrect;
    private int timesIncorrect;
    private int total;

    public static MistakeDataSet dummy() {
        return MistakeDataSet.builder().question("Q").correctAnswer("A").build();
    }

    public static List<MistakeDataSet> dummyList(int i) {
        return IntStream.rangeClosed(0, i).mapToObj(j -> MistakeDataSet.dummy()).collect(Collectors.toList());
    }

    public static List<MistakeDataSet> from(List<LanguageMistakeDetailTO> answerTOList) {
        return answerTOList.stream().map(MistakeDataSet::from).collect(Collectors.toList());
    }

    public static MistakeDataSet from(LanguageMistakeDetailTO mistakeDetailTO) {
        return MistakeDataSet.builder()
                .correctAnswer(mistakeDetailTO.getCorrectAnswer())
                .givenAnswers(mistakeDetailTO.getGivenAnswers())
                .question(mistakeDetailTO.getQuestion())
                .timesCorrect(mistakeDetailTO.getTimesCorrect())
                .timesIncorrect(mistakeDetailTO.getTimesIncorrect())
                .total(mistakeDetailTO.getTotal())
                .build();
    }
}

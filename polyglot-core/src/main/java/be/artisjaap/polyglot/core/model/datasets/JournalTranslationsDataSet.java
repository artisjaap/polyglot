package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@Data
public class JournalTranslationsDataSet {
    private LocalDateTime timestamp;
    private String translationId;
    private String question;
    private String givenAnswer;
    private String expectedAnswer;
    private Boolean correct;

    public static List<JournalTranslationsDataSet> dummyList(int i) {
        return IntStream.rangeClosed(0, i).mapToObj(j -> JournalTranslationsDataSet.dummy(j%2==0)).collect(Collectors.toList());
    }

    public static JournalTranslationsDataSet dummy(boolean correct) {
        return builder()
                .timestamp(LocalDateUtils.now())
                .translationId("id")
                .question("Question")
                .givenAnswer("Answer")
                .expectedAnswer(correct?"Answer":"Given Answer")
                .correct(correct)
                .build();
    }

    public static List<JournalTranslationsDataSet> from(List<TranslationJournalTO> translationJournalList) {
        return translationJournalList.stream()
                .map(JournalTranslationsDataSet::from)
                .collect(Collectors.toList());
    }

    public static JournalTranslationsDataSet from(TranslationJournalTO translationJournalTO){
        return builder()
                .timestamp(translationJournalTO.timestamp())
                .translationId(translationJournalTO.translationId())
                .question(translationJournalTO.question())
                .givenAnswer(translationJournalTO.givenAnswer())
                .expectedAnswer(translationJournalTO.expectedAnswer())
                .correct(translationJournalTO.correct())
                .build();

    }
}

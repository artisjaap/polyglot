package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.TranslationTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@Data
public class TranslationDataSet {
    private String languageA;
    private String languageB;

    public static TranslationDataSet dummy() {
        return TranslationDataSet.builder().languageA("DUTCH").languageB("FRENCH").build();
    }

    public static List<TranslationDataSet> dummyList(int i) {
        return IntStream.rangeClosed(0, i).mapToObj(j -> TranslationDataSet.dummy()).collect(Collectors.toList());
    }

    public static Set<TranslationDataSet> from(TranslationTO to) {
        return to.languageA().stream()
                .map(languageA -> TranslationDataSet.builder()
                        .languageA(languageA)
                        .languageB(to.languageB().stream().collect(Collectors.joining(" / ")))
                        .build())
                .collect(Collectors.toSet());

    }

    public static List<TranslationDataSet> from(List<TranslationTO> translations) {
        return translations.stream().flatMap(translation -> from(translation).stream()).collect(Collectors.toList());
    }

}

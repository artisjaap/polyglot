package be.artisjaap.polyglot.core.model.verbs;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
public class VerbTense {
    private TimeOfVerb timeOfVerb;
    @Builder.Default
    private Set<VerbTranslation> verbTranslations = new HashSet<>();

    public void loadTranslations(List<VerbTranslation> translations) {
        List<VerbFinite> verbFinites = translations.stream().map(VerbTranslation::getVerbFinite).collect(Collectors.toList());
        List<VerbTranslation> toRemove = verbTranslations.stream()
                .filter(translation -> verbFinites.contains(translation.getVerbFinite()))
                .collect(Collectors.toList());

        verbTranslations.removeAll(toRemove);

        verbTranslations.addAll(translations);
    }
}

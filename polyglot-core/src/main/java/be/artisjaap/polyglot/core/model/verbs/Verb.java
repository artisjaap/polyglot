package be.artisjaap.polyglot.core.model.verbs;

import be.artisjaap.common.model.AbstractDocument;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class Verb extends AbstractDocument {
    private ObjectId languagePairId;
    private String infinitiveLanguageA;
    private String infinitiveLanguageB;
    @Builder.Default
    private Set<VerbTense> verbTenses = new HashSet<>();

    public VerbTense tense(TimeOfVerb tense){
        return verbTenses.stream().filter(verbTense -> verbTense.getTimeOfVerb() == tense)
                .findFirst()
                .orElseGet(() -> {
                    VerbTense build = VerbTense.builder().timeOfVerb(tense).build();
                    verbTenses.add(build);
                    return build;
                });
    }
}

package be.artisjaap.polyglot.core.action.to.verbs;

import be.artisjaap.polyglot.core.model.verbs.VerbTense;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@Data
public class NewOrUpdateVerbTO {

    @NonNull
    private String infinitiveLanguageA;
    @NonNull
    private String infinitiveLanguageB;
    @NonNull
    private VerbTenseTO verbTense;
    @NonNull
    private String languagePairId;


}

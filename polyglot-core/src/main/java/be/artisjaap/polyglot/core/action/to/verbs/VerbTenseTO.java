package be.artisjaap.polyglot.core.action.to.verbs;

import be.artisjaap.polyglot.core.model.verbs.TimeOfVerb;
import be.artisjaap.polyglot.core.model.verbs.VerbTense;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class VerbTenseTO {
    private TimeOfVerb tenseOfVerb;
    private List<VerbFiniteTO> verbFiniteTOList;
}

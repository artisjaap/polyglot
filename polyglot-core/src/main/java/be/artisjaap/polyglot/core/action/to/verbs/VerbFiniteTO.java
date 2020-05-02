package be.artisjaap.polyglot.core.action.to.verbs;

import be.artisjaap.polyglot.core.model.verbs.VerbFinite;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VerbFiniteTO {
    private VerbFinite verbFinite;
    private String languageA;
    private String langaugeB;
}

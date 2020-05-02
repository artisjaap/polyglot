package be.artisjaap.polyglot.core.model.verbs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerbTranslation {
    private VerbFinite verbFinite;
    private String languageA;
    private String languageB;
}

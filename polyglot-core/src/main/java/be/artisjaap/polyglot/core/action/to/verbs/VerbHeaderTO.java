package be.artisjaap.polyglot.core.action.to.verbs;

import be.artisjaap.common.model.ReferenceableTO;
import be.artisjaap.polyglot.core.model.verbs.VerbTense;

import java.util.List;

public class VerbHeaderTO extends ReferenceableTO {
    private String languageA;
    private String languageB;
    private List<VerbTense> availableTenses;


}

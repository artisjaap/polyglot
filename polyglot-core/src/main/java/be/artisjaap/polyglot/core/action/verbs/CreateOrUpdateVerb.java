package be.artisjaap.polyglot.core.action.verbs;

import be.artisjaap.common.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.to.verbs.NewOrUpdateVerbTO;
import be.artisjaap.polyglot.core.model.verbs.Verb;
import be.artisjaap.polyglot.core.model.verbs.VerbRepository;
import be.artisjaap.polyglot.core.model.verbs.VerbTense;
import be.artisjaap.polyglot.core.model.verbs.VerbTranslation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static be.artisjaap.common.utils.MongoUtils.toObjectId;

@Component
public class CreateOrUpdateVerb {

    private final VerbRepository verbRepository;

    public CreateOrUpdateVerb(VerbRepository verbRepository) {
        this.verbRepository = verbRepository;
    }

    public void forVerb(NewOrUpdateVerbTO verbOrNew){
        Verb verb = verbRepository.findByInfinitiveLanguageAAndInfinitiveLanguageBAndLanguagePairId(
                verbOrNew.getInfinitiveLanguageA(),
                verbOrNew.getInfinitiveLanguageB(),
                toObjectId(verbOrNew.getLanguagePairId()))
                .orElseGet(() -> Verb.builder()
                        .infinitiveLanguageA(verbOrNew.getInfinitiveLanguageA())
                        .infinitiveLanguageB(verbOrNew.getInfinitiveLanguageB())
                        .languagePairId(toObjectId(verbOrNew.getLanguagePairId()))
                        .build());
        VerbTense tense = verb.tense(verbOrNew.getVerbTense().getTenseOfVerb());
        List<VerbTranslation> translations = verbOrNew.getVerbTense().getVerbFiniteTOList()
                .stream()
                .map(v -> VerbTranslation.builder()
                        .languageA(v.getLanguageA())
                        .languageB(v.getLangaugeB())
                        .verbFinite(v.getVerbFinite())
                        .build())
                .collect(Collectors.toList());

        tense.loadTranslations(translations);


        verbRepository.save(verb);

    }
}

package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.polyglot.core.action.assembler.TranslationAssembler;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindTranslations {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationAssembler translationAssembler;


    public Optional<TranslationTO> byId(String translationId) {
        return translationRepository.findById(new ObjectId(translationId)).map(translationAssembler::assembleTO);
    }

    public List<TranslationTO> containing(String languagePairId, List<String> languageA) {
        List<Translation> translations = translationRepository.findByLanguagePairIdAndLanguageAIn(new ObjectId(languagePairId), languageA);
        return translations.stream().map(translationAssembler::assembleTO).collect(Collectors.toList());
    }

    public List<TranslationTO> latestFor(String userId, int count){
        return new ArrayList<>();
    }

}

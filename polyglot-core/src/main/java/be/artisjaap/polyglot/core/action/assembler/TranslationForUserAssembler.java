package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.NewTranslationForUserTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TranslationForUserAssembler {
    @Autowired
    private TranslationAssembler translationPairAssembler;

    public TranslationsForUserTO assembleTO(NewTranslationForUserTO to, List<Translation> translations, List<TranslationTO> additionalTranslations) {
        return TranslationsForUserTO.newBuilder()
                .withUserId(to.userId())
                .withLanguagePairId(to.languagePairId())
                .addTranslations(translationPairAssembler.assembleTOs(translations))
                .addTranslations(additionalTranslations)
                .build();
    }
}

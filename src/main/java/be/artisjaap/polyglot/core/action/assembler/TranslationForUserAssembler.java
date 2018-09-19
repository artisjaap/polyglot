package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.NewTranslationForUserTO;
import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TranslationForUserAssembler {
    @Autowired
    private TranslationPairAssembler translationPairAssembler;

    public TranslationsForUserTO assembleTO(NewTranslationForUserTO to, List<Translation> translations) {
        return TranslationsForUserTO.newBuilder()
                .withUserId(to.userId())
                .withLanguagePairId(to.languagePairId())
                .withTranslations(translationPairAssembler.assembleTOs(translations))
                .build();
    }
}

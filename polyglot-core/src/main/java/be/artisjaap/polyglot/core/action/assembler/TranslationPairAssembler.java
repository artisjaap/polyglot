package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.TranslationPairTO;
import be.artisjaap.polyglot.core.model.Translation;
import org.springframework.stereotype.Component;

@Component
public class TranslationPairAssembler implements Assembler<TranslationPairTO, Translation> {


    @Override
    public TranslationPairTO assembleTO(Translation doc) {
        return TranslationPairTO.newBuilder()
                .withId(doc.getId().toString())
                .withLanguageA(doc.getLanguageA())
                .withLanguageB(doc.getLanguageB())
                .build();
    }

    @Override
    public Translation assembleEntity(TranslationPairTO newTranslationPairTO) {
        return null;
    }
}

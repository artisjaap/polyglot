package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.model.Translation;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class NewTranslationAssembler implements Assembler<NewSimpleTranslationPairTO, Translation> {
    @Override
    public NewSimpleTranslationPairTO assembleTO(Translation doc) {
        return null;
    }

    @Override
    public Translation assembleEntity(NewSimpleTranslationPairTO simpleTranslationPairTO){
        throw new UnsupportedOperationException("Use assembleEntity(NewSimpleTranslationPairTO simpleTranslationPairTO, ObjectId languagePairId)");
    }

    public Translation assembleEntity(NewSimpleTranslationPairTO simpleTranslationPairTO, ObjectId languagePairId) {
        return Translation.newBuilder()
                .withLanguagePairId(languagePairId)
                .withLanguageA(simpleTranslationPairTO.languageFrom())
                .withLanguageB(simpleTranslationPairTO.languageTO())
                .build();
    }
}

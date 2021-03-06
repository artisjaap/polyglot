package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.model.Translation;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class TranslationAssembler implements Assembler<TranslationTO, Translation> {


    @Override
    public TranslationTO assembleTO(Translation doc) {
        return TranslationTO.newBuilder()
                .forDocument(doc)
                .withLanguageA(doc.getLanguageA())
                .withLanguageB(doc.getLanguageB())
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .build();
    }

    @Override
    public Translation assembleEntity(TranslationTO translationTO) {
        return Translation.newBuilder()
                .forDocument(translationTO)
                .withLanguageA(translationTO.languageA())
                .withLanguageB(translationTO.languageB())
                .withLanguagePairId(new ObjectId(translationTO.languagePairId()))
                .build();
    }
}

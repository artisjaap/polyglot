package be.artisjaap.i18n.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.i18n.action.to.TranslationTO;
import be.artisjaap.i18n.model.Translation;
import org.springframework.stereotype.Component;

@Component
public class TranslationAssembler implements Assembler<TranslationTO, Translation> {
    @Override
    public TranslationTO assembleTO(Translation doc) {
        return TranslationTO.newBuilder()
                .withBundleName(doc.getBundleName())
                .withKey(doc.getKey())
                .withLanguageIsoCode(doc.getLanguageIsoCode())
                .withTranslation(doc.getTranslation())
                .build();
    }

    @Override
    public Translation assembleEntity(TranslationTO translationTO) {
        return Translation.newBuilder()
                .withBundleName(translationTO.bundleName())
                .withKey(translationTO.key())
                .withLanguageIsoCode(translationTO.languageIsoCode())
                .withTranslation(translationTO.translation())
                .build();
    }
}

package be.artisjaap.i18n.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.i18n.action.to.NewTranslationTO;
import be.artisjaap.i18n.model.Translation;
import org.springframework.stereotype.Component;

@Component
public class I18nNewTranslationAssembler implements Assembler<NewTranslationTO, Translation> {
    @Override
    public NewTranslationTO assembleTO(Translation doc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Translation assembleEntity(NewTranslationTO translationTO) {
        return Translation.newBuilder()
                .withBundleName(translationTO.bundleName())
                .withKey(translationTO.key())
                .withLanguageIsoCode(translationTO.languageIsoCode())
                .withTranslation(translationTO.translation())
                .build();
    }
}

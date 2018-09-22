package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.NewTranslationForUserAssembler;
import be.artisjaap.polyglot.core.action.assembler.TranslationForUserAssembler;
import be.artisjaap.polyglot.core.action.to.NewSimpleTranslationPairTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationForUserFromFileTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationForUserTO;
import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.action.to.translationsfromfile.TranslationRecord;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterTranslation {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private NewTranslationForUserAssembler newTranslationForUserAssembler;

    @Autowired
    private TranslationForUserAssembler translationForUserAssembler;

    @Autowired
    private InitTranslationForPractice initTranslationForPractice;


    public TranslationsForUserTO forAllWords(NewTranslationForUserTO to){
        List<Translation> translations = newTranslationForUserAssembler.assembleAllEntities(to);
        translationRepository.saveAll(translations);

        TranslationsForUserTO translationsForUserTO = translationForUserAssembler.assembleTO(to, translations);

        initTranslationForPractice.forAll(translationsForUserTO);

        return translationsForUserTO;
    }

    public TranslationsForUserTO forAllWords(NewTranslationForUserFromFileTO to){
        List<TranslationRecord> translationRecords = new CsvToBeanBuilder(to.reader())
                .withType(TranslationRecord.class).build().parse();

        NewTranslationForUserTO newTranslationForUserTO = NewTranslationForUserTO.newBuilder()
                .withLanguagePairId(to.languagePairId())
                .withUserId(to.userId())
                .withTranslations(translationRecords.stream().map(r -> NewSimpleTranslationPairTO.newBuilder()
                        .withLanguageFrom(r.getLanguageA())
                        .withLanguageTO(r.getLanguageB())
                        .build()).collect(Collectors.toList()))
                .build();

        return forAllWords(newTranslationForUserTO);
    }
}

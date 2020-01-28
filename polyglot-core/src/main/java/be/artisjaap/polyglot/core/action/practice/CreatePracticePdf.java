package be.artisjaap.polyglot.core.action.practice;

import be.artisjaap.common.utils.InfinitRandomDataStreamer;
import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.documents.DatasetProviderFactory;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.model.datasets.TranslationDataSet;
import be.artisjaap.polyglot.core.action.to.CreatePracticePdfTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.translation.FindTranslations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CreatePracticePdf {

    @Resource
    private FindTranslations findTranslations;

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private GenerateDocument generateDocument;

    public Optional<byte[]> forData(CreatePracticePdfTO practicePdfTO){
        List<TranslationTO> translationTOS = findTranslationsForSettings(practicePdfTO);
        InfinitRandomDataStreamer infinitRandomDataStreamer = InfinitRandomDataStreamer.fromDataList(translationTOS);

        Integer numberOfWords = practicePdfTO.getNumberOfWords()==0?translationTOS.size(): practicePdfTO.getNumberOfWords();
        List<TranslationTO> randomTranslationsList = IntStream.rangeClosed(1, numberOfWords)
                .mapToObj(i -> (TranslationTO) infinitRandomDataStreamer.next())
                .collect(Collectors.toList());

        LanguagePairTO languagePairTO = findLanguagePair.byId(practicePdfTO.getLanguagePairId());

        return generateDocument.forDocument(BriefConfigTO.builder()
                .code("WORD_PRACTICE_SHEET_WITH_ANSWERS")
                .datasetProvider(DatasetProviderFactory.create()
                        .translationsFrom(randomTranslationsList)
                        .languagePairDataSetFrom(languagePairTO))
                .opslagSettingsTO(BriefLocatieFactory.briefNietOpslaan())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .taal("NL")
                .build());

    }

    private List<TranslationTO> findTranslationsForSettings(CreatePracticePdfTO practicePdfTO) {
        if(practicePdfTO.getLessonId() != null){
            return findTranslations.allWordsForLesson(practicePdfTO.getLessonId());
        }
        return findTranslations.allWordsForLanguagePair(practicePdfTO.getLanguagePairId());
    }
}

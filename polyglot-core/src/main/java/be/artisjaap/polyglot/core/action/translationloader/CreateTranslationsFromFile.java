package be.artisjaap.polyglot.core.action.translationloader;

import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.translationsfromfile.TranslationRecord;
import be.artisjaap.polyglot.core.action.to.translationsfromfile.VerbTranslationRecord;
import be.artisjaap.polyglot.core.action.to.verbs.NewOrUpdateVerbTO;
import be.artisjaap.polyglot.core.action.to.verbs.VerbFiniteTO;
import be.artisjaap.polyglot.core.action.to.verbs.VerbTenseTO;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.translationloader.ParsedVerb;
import be.artisjaap.polyglot.core.action.translationloader.TranslationFileLoader;
import be.artisjaap.polyglot.core.action.verbs.CreateOrUpdateVerb;
import be.artisjaap.polyglot.core.model.verbs.VerbFinite;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
public class CreateTranslationsFromFile {
    private final CreateTranslation createTranslation;
    private final CreateOrUpdateVerb createOrUpdateVerb;
    private final CreateLesson createLesson;

    public CreateTranslationsFromFile(CreateTranslation createTranslation, CreateOrUpdateVerb createOrUpdateVerb, CreateLesson createLesson){
        this.createTranslation = createTranslation;
        this.createOrUpdateVerb = createOrUpdateVerb;
        this.createLesson = createLesson;
    }

    public TranslationsForUserTO saveTranslations(NewTranslationForUserFromFileTO to) {
        TranslationFileLoader pr = TranslationFileLoader.of(to.reader());

        if(pr.isVerb()){
            ParsedVerb verbTag = pr.parsedVerb().orElseThrow(() -> new IllegalStateException("Cannot parse Verb tag"));
            ParsedTense tenseTag = pr.parsedTense().orElseThrow(() -> new IllegalStateException("No or invalid tense tag"));

            List<VerbTranslationRecord> translations = new CsvToBeanBuilder(pr.getReader())
                    .withType(VerbTranslationRecord.class).build().parse();

            NewOrUpdateVerbTO newOrUpdateVerbTO = NewOrUpdateVerbTO.builder()
                    .languagePairId(to.languagePairId())
                    .infinitiveLanguageA(verbTag.getInfiniteLanguageA())
                    .infinitiveLanguageB(verbTag.getInfiniteLanguageB())
                    .verbTense(VerbTenseTO.builder()
                            .tenseOfVerb(tenseTag.getTimeOfVerb())
                            .verbFiniteTOList(mapToFiniteList(translations))
                            .build())
                    .build();

            createOrUpdateVerb.forVerb(newOrUpdateVerbTO);

            return TranslationsForUserTO.newBuilder().build();
        }else{




            List<TranslationRecord> translationRecords = new CsvToBeanBuilder(pr.getReader())
                    .withType(TranslationRecord.class).build().parse();

            NewTranslationsForUserTO newTranslationsForUserTO = NewTranslationsForUserTO.newBuilder()
                    .withLanguagePairId(to.languagePairId())
                    .withUserId(to.userId())
                    .withTranslations(translationRecords.stream().map(r -> NewSimpleTranslationPairTO.newBuilder()
                            .withLanguageFrom(r.getLanguageA())
                            .withLanguageTO(r.getLanguageB())
                            .build()).collect(Collectors.toList()))
                    .build();

            TranslationsForUserTO translationsForUserTO = createTranslation.forAllWords(newTranslationsForUserTO);

            if( pr.parsedLesson().isPresent()) {

                LessonTO lessonTO = createLesson.create(NewLessonTO.builder()
                        .name(pr.parsedLesson().get().getLessonName())
                        .languagePairId(to.languagePairId())
                        .userId(to.userId())
                        .translationsIds(translationsForUserTO.translations().stream().map(TranslationTO::id).collect(Collectors.toList()))
//                        .tags(pr.tags())
                        .build());
                translationsForUserTO.setLessonHeader(LessonHeaderTO.newBuilder()
                        .withId(lessonTO.id())
                        .withLanguagePairId(lessonTO.languagePairId())
                        .withName(lessonTO.name())
                        .withUserId(lessonTO.userId())
                        .build()
                );
            }


            return translationsForUserTO;


        }
    }

    private List<VerbFiniteTO> mapToFiniteList(List<VerbTranslationRecord> translations) {
        if(translations.size() == 8){
            return IntStream.rangeClosed(1,8).mapToObj(i -> {
                VerbTranslationRecord verbTranslationRecord = translations.get(i-1);
                return VerbFiniteTO.builder()
                        .languageA(verbTranslationRecord.getLanguageA())
                        .langaugeB(verbTranslationRecord.getLanguageB())
                        .verbFinite(VerbFinite.getForIndex(i))
                        .build();
            }).collect(Collectors.toList());
        }
        throw new UnsupportedOperationException("only full verb conjucation supported");
    }


}



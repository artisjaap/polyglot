package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.polyglot.core.action.assembler.NewTranslationForUserAssembler;
import be.artisjaap.polyglot.core.action.assembler.TranslationForUserAssembler;
import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.lesson.CreateNewWordForLesson;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.translationsfromfile.TranslationRecord;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class CreateTranslation {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private NewTranslationForUserAssembler newTranslationForUserAssembler;

    @Autowired
    private TranslationForUserAssembler translationForUserAssembler;

    @Autowired
    private CreateInitTranslationForPractice createInitTranslationForPractice;

    @Autowired
    private FindTranslations findTranslations;

    @Autowired
    private CreateLesson createLesson;

    @Autowired
    private CreateNewWordForLesson createNewWordForLesson;

    public TranslationTO forTranslation(NewTranslationTO to) {
        return forAllWords(NewTranslationsForUserTO.newBuilder()
                .withUserId(to.getUserId())
                .withLanguagePairId(to.getLanguagePairId())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder()
                        .withLanguageFrom(to.getLanguageFrom())
                        .withLanguageTO(to.getLanguageTo())
                        .build())
                .build())
                .translations()
                .iterator()
                .next();

    }

    public TranslationTO forTranslation(NewTranslationInLessonTO to) {
        return createNewWordForLesson.forWord(NewWordForLessonTO.newBuilder()
                .languageFrom(to.getLanguageFrom())
                .languageTO(to.getLanguageTO())
                .lessonId(to.getLessonId())
                .build());

    }

    public TranslationsForUserTO forAllWords(NewTranslationsForUserTO to) {
        List<Translation> translations = newTranslationForUserAssembler.assembleAllEntities(to);

        List<String> languageA = translations.stream().map(Translation::getLanguageA).collect(Collectors.toList());
        List<TranslationTO> duplicates = findTranslations.containing(to.languagePairId(), languageA);

        List<Translation> newTranslations = filterDuplicate(translations, duplicates);

        translationRepository.saveAll(newTranslations);

        TranslationsForUserTO translationsForUserTO = translationForUserAssembler.assembleTO(to, newTranslations, duplicates);

        createInitTranslationForPractice.forAll(translationsForUserTO);

        return translationsForUserTO;
    }

    private List<Translation> filterDuplicate(List<Translation> translations, List<TranslationTO> duplicates) {
        List<String> languageA = duplicates.stream().map(TranslationTO::languageA).collect(Collectors.toList());

        return translations.stream().filter(translation -> !languageA.contains(translation.getLanguageA()))
                .collect(Collectors.toList());
    }

    public TranslationsForUserTO forAllWords(NewTranslationForUserFromFileTO to) {

        ProxyReader pr = new ProxyReader(to.reader());


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

        TranslationsForUserTO translationsForUserTO = forAllWords(newTranslationsForUserTO);

        if( pr.lessonName().isPresent()) {

            LessonTO lessonTO = createLesson.create(NewLessonTO.builder()
                    .name(pr.lessonName().get())
                    .languagePairId(to.languagePairId())
                    .userId(to.userId())
                    .translationsIds(translationsForUserTO.translations().stream().map(TranslationTO::id).collect(Collectors.toList()))
                    .tags(pr.tags())
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

class ProxyReader {

    private final BufferedReader reader;
    private String lessonName = null;
    private Set<String> tags = new HashSet<>();

    public Optional<String> lessonName() {
        return Optional.ofNullable(lessonName);
    }

    public Set<String> tags() {
        return tags;
    }

    public ProxyReader(Reader reader) {
        this.reader = new BufferedReader(reader);

    }

    public Reader getReader() {
        byte[] bytes = findBytes();

        return new InputStreamReader(new ByteArrayInputStream(bytes));
    }

    private byte[] findBytes() {
        String line = null;
        try {
            line = reader.readLine();

            StringBuilder sb = new StringBuilder();

            while (line != null) {
                if (line.startsWith("@")) {
                    if (line.startsWith("@Lesson")) {
                        lessonName = line.substring(8);
                    }
                    if (line.startsWith("@Tags")) {
                        tags = Arrays.asList(line.substring(6).split(","))
                                .stream()
                                .collect(Collectors.toSet());
                    }
                } else {
                    sb.append(line).append("\r\n");
                }
                line = reader.readLine();
            }

            return sb.toString().getBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}

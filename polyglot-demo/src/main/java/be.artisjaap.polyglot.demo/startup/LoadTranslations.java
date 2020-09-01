package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.pairs.CreateLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.translation.UpdateStatusTranslation;
import be.artisjaap.polyglot.core.action.translationloader.CreateTranslationsFromFile;
import be.artisjaap.polyglot.core.action.user.FindUser;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class LoadTranslations extends AbstractInitScript {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Resource
    private CreateTranslation createTranslation;

    @Resource
    private CreateTranslationsFromFile createTranslationsFromFile;

    @Resource
    private UpdateStatusTranslation updateStatusTranslation;

    @Resource
    private CreateLesson createLesson;

    @Resource
    private CreateLanguagePair createLanguagePair;

    @Resource
    private FindUser findUser;

    @Resource
    private FindLanguagePair findLanguagePair;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String omschrijving() {
        return "Load lessons";
    }

    @Override
    public String getVersion() {
        return "Demo";
    }

    @Override
    public Integer cardinality() {
        return 1010;
    }

    @Override
    public void execute() {
        String userId = findUser.byUsername("stijn").map(UserTO::id).orElseThrow(() -> new IllegalStateException("User stijn not found"));





        LanguagePairTO languagePairToNlEn = createLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userId)
                .withLanguageFrom("Nederlands")
                .withLanguageTo("Engels")
                .build());
        logger.info("LanguagePair created with id: " + languagePairToNlEn.id());

        LanguagePairTO languagePairToNlFr = createLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userId)
                .withLanguageFrom("Nederlands")
                .withLanguageTo("Frans")
                .build());
        logger.info("LanguagePair created with id: " + languagePairToNlFr.id());

        LanguagePairTO languagePairToNlLa = createLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userId)
                .withLanguageFrom("Nederlands")
                .withLanguageTo("Latijn")
                .build());
        logger.info("LanguagePair created with id: " + languagePairToNlLa.id());

        TranslationsForUserTO translationsForUserTO = createTranslation.forAllWords(NewTranslationsForUserTO.newBuilder()
                .withLanguagePairId(languagePairToNlEn.id())
                .withUserId(languagePairToNlEn.userId())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("EEN")).withLanguageTO(Set.of("ONE")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("TWEE")).withLanguageTO(Set.of("TWO")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("DRIE")).withLanguageTO(Set.of("THREE")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("VIER")).withLanguageTO(Set.of("FOUR")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("VIJF")).withLanguageTO(Set.of("FIVE")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("ZES")).withLanguageTO(Set.of("SIX")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("ZEVEN")).withLanguageTO(Set.of("SEVEN")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("ACHT")).withLanguageTO(Set.of("EIGHT")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("NEGEN")).withLanguageTO(Set.of("NINE")).build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(Set.of("TIEN")).withLanguageTO(Set.of("TEN")).build())
                .build());


        updateStatusTranslation.allInStatusTo(userId, languagePairToNlEn.id(), ProgressStatus.NEW, ProgressStatus.IN_PROGRESS);

        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withLanguagePairId(languagePairToNlEn.id())
                .withLessonTitle("Counting to ten")
                .withMaxNumberOfWords(10)
                .withUserId(userId)
                .build());
        logger.info("Lesson created with id: " + lessonTO.id());







        

        try {
            System.out.println("CURRENT PATH: " + new File("./lessons/nlla").getAbsolutePath());
            Arrays.asList(applicationContext.getResources("/lessons")).forEach(file -> logger.info("App resource: " + file));
            getResourceFiles("/lessons").forEach(file -> logger.info("Resource " + file));

            getResourceFiles("/lessons/nlla").forEach(file -> createTranslationsFromFile.saveTranslations(NewTranslationForUserFromFileTO.newBuilder()
                                    .withUserId(userId)
                                    .withLanguagePairId(languagePairToNlLa.id())
                                    .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/nlla/"  + file), Charset.forName("UTF-8")))
                                    .build()));

            getResourceFiles("/lessons/nlfr").forEach(file -> createTranslationsFromFile.saveTranslations(NewTranslationForUserFromFileTO.newBuilder()
                    .withUserId(userId)
                    .withLanguagePairId(languagePairToNlFr.id())
                    .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/nlfr/"  + file), Charset.forName("UTF-8")))
                    .build()));
            
            Files.walk(Path.of(new File("./lessons/nlla").toURI())).forEach(path -> {
                System.out.println("processing.." + path);

                try {
                    if (path.toFile().isFile()){
                        createTranslationsFromFile.saveTranslations(NewTranslationForUserFromFileTO.newBuilder()
                                .withUserId(userId)
                                .withLanguagePairId(languagePairToNlLa.id())
                                .withReader(new InputStreamReader(new FileInputStream(path.toFile()), Charset.forName("UTF-8")))
                                .build());
                    }
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            });
            
        } catch (Exception e) {
            logger.error(e);
        }
        ;
    }

    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}

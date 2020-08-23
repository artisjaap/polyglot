package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.pairs.CreateLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.translation.UpdateStatusTranslation;
import be.artisjaap.polyglot.core.action.user.FindUser;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStreamReader;

@Component
public class LoadTranslations extends AbstractInitScript {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Resource
    private CreateTranslation createTranslation;

    @Autowired
    private UpdateStatusTranslation updateStatusTranslation;

    @Autowired
    private CreateLesson createLesson;

    @Resource
    private CreateLanguagePair createLanguagePair;

    @Resource
    private FindUser findUser;

    @Resource
    private FindLanguagePair findLanguagePair;

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
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("EEN").withLanguageTO("ONE").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("TWEE").withLanguageTO("TWO").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("DRIE").withLanguageTO("THREE").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("VIER").withLanguageTO("FOUR").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("VIJF").withLanguageTO("FIVE").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("ZES").withLanguageTO("SIX").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("ZEVEN").withLanguageTO("SEVEN").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("ACHT").withLanguageTO("EIGHT").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("NEGEN").withLanguageTO("NINE").build())
                .addTranslation(NewSimpleTranslationPairTO.newBuilder().withLanguageFrom("TIEN").withLanguageTO("TEN").build())
                .build());


        updateStatusTranslation.allInStatusTo(userId, languagePairToNlEn.id(), ProgressStatus.NEW, ProgressStatus.IN_PROGRESS);

        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withLanguagePairId(languagePairToNlEn.id())
                .withLessonTitle("Counting to ten")
                .withMaxNumberOfWords(10)
                .withUserId(userId)
                .build());
        logger.info("Lesson created with id: " + lessonTO.id());



        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(userId)
                .withLanguagePairId(languagePairToNlFr.id())
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/QE6b-planet-magasin.csv")))
                .build());

        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(userId)
                .withLanguagePairId(languagePairToNlFr.id())
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/QE6b-planet-7-Carnaval.csv")))
                .build());

        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(userId)
                .withLanguagePairId(languagePairToNlLa.id())
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/Lat1-thema-theatrum.txt")))
                .build());


    }
}

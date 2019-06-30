package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.backup.action.CollectionInfo;
import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.pairs.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.ChangeStatusTranslation;
import be.artisjaap.polyglot.core.action.translation.FindTranslationsInPractice;
import be.artisjaap.polyglot.core.action.translation.RegisterTranslation;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterUsers extends AbstractInitScript {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @Autowired
    private RegisterTranslation registerTranslation;

    @Autowired
    private CreateLesson createLesson;


    @Autowired
    private ChangeStatusTranslation changeStatusTranslation;

    @Autowired
    private CollectionInfo collectionInfo;

    @Override
    public String getVersion() {
        return "DEMO";
    }

    @Override
    public String omschrijving() {
        return "Generate Users";
    }

    @Override
    public Integer cardinality() {
        return 1000;
    }

    @Override
    public void execute() {
        collectionInfo.allCollections();

        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("stijn").withPassword("abc").withRole("STUDENT").build());
        logger.info("User created with id: " + userTO.id());

        LanguagePairTO languagePairTO = registerLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userTO.id())
                .withLanguageFrom("Dutch")
                .withLanguageTo("English")
                .build());
        logger.info("LanguagePair created with id: " + languagePairTO.id());

        TranslationsForUserTO translationsForUserTO = registerTranslation.forAllWords(NewTranslationForUserTO.newBuilder()
                .withLanguagePairId(languagePairTO.id())
                .withUserId(languagePairTO.userId())
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


        changeStatusTranslation.allInStatusTo(userTO.id(), languagePairTO.id(), ProgressStatus.NEW, ProgressStatus.IN_PROGRESS);

        LessonTO lessonTO = createLesson.automaticallyFor(NewAutomaticLessonTO.newBuilder()
                .withLanguagePairId(languagePairTO.id())
                .withLessonTitle("Counting to ten")
                .withMaxNumberOfWords(10)
                .withUserId(userTO.id())
                .build());
        logger.info("Lesson created with id: " + lessonTO.id());



    }
}

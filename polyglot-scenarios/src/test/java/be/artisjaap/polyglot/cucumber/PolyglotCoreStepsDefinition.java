package be.artisjaap.polyglot.cucumber;

import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.core.action.*;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.*;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.bson.types.ObjectId;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(Cucumber.class)
@ContextConfiguration( classes = PolyglotApplication.class)
public class PolyglotCoreStepsDefinition {
    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private RegisterTranslation registerTranslation;

    @Autowired
    private CreateLesson createLesson;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @Autowired
    private FindUser findUser;

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private PracticeWords practiceWords;

    @Autowired
    private UpdateUserSettings updateUserSettings;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TestForLesson testForLesson;

    @Given("^a user named (.*)$")
    public void eenGebruikerMetNaam(String naam)  {
        registerUser.newUser(NewUserTO.newBuilder()
                .withUsername(naam)
                .build());
    }

    @And("^(.*) creates language pair (.*)-(.*)$")
    public void maaktEenTalenpaar(String username, String languageFrom, String languageTo) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        registerLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(user.id())
                .withLanguageFrom(languageFrom)
                .withLanguageTo(languageTo)
                .build());
    }

    @And("^(.*) adds the following translations on language pair (.*)-(.*)$")
    public void voegVolgendeVertalingenToeOp(String username, String languageFrom, String languageTo, List<LanguagePairGherkinRow> translations) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));

        LanguagePairTO languagePair = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(() -> new IllegalStateException("Expected language pair for user"));


        List<NewSimpleTranslationPairTO> translationParams = translations.stream()
                .map(t -> NewSimpleTranslationPairTO.newBuilder().withLanguageFrom(t.languageA)
                        .withLanguageTO(t.languageB).build()).collect(Collectors.toList());

        registerTranslation.forAllWords(NewTranslationForUserTO.newBuilder()
                .withUserId(user.id())
                .withTranslations(translationParams)
                .withLanguagePairId(languagePair.id())
                .build()
        );
    }


    @Given("^a user (.*) with default dataset$")
    public void eenStandaardGebruikerDataset(String naam) {
        eenGebruikerMetNaam(naam);
        maaktEenTalenpaar(naam, "NL", "FR");
        List<LanguagePairGherkinRow> vertalingen = new ArrayList<>();
        vertalingen.add(new LanguagePairGherkinRow("een", "un"));
        vertalingen.add(new LanguagePairGherkinRow("twee", "deux"));
        vertalingen.add(new LanguagePairGherkinRow("drie", "trois"));
        vertalingen.add(new LanguagePairGherkinRow("vier", "quatre"));
        vertalingen.add(new LanguagePairGherkinRow("vijf", "cinque"));
        vertalingen.add(new LanguagePairGherkinRow("zes", "six"));
        vertalingen.add(new LanguagePairGherkinRow("zeven", "sept"));
        vertalingen.add(new LanguagePairGherkinRow("acht", "huit"));
        vertalingen.add(new LanguagePairGherkinRow("negen", "neuf"));
        vertalingen.add(new LanguagePairGherkinRow("tien", "dix"));
        voegVolgendeVertalingenToeOp("Tom", "NL", "FR", vertalingen);
    }

    @When("^(.*) starts practicing his words with following settings$")
    public void zijnNietGekendeWoordenBegintTeOefenenMetInstellingen(String username, Map<String, String> settings ) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        updateUserSettings.forUser(UserSettingsUpdateTO.newBuilder()
                .withUserId(user.id())
                .withFlagAsKnowWhenWinningStreakHitsX(propertyAsInteger(settings, "flag as know when winning streak is", "5"))
                .withInitialNumberOnPracticeWords(propertyAsInteger(settings, "number of words to start with", "5"))
                .withNewWordEveryXexcersises(propertyAsInteger(settings, "introduce new word every X exercises", "20"))
                .build());

        String languageFrom = settings.get("question lanquage");
        String languageTo = settings.get("answer language");
        LanguagePairTO languagePairTO = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(IllegalStateException::new);
        OrderType orderType = languagePairTO.languageFrom().equalsIgnoreCase(languageFrom)?OrderType.NORMAL:OrderType.REVERSE;

        List<PracticeWordTO> practiceWordTOS = practiceWords.giveCurrentListToPractice(user.id(), languagePairTO.id(), orderType);
    }

    private Integer propertyAsInteger(Map<String, String> map, String key, String defaultValue){
        return Integer.parseInt(map.getOrDefault(key, defaultValue));
    }

    @When("^(.*) starts to practice in (.*)-(.*) (NORMAL|REVERSE|RANDOM) order, (\\d+) exercises, adding a new word every (\\d+) turns$")
    public void tomStartsToPracticeInNormalOrderExercisesAddingANewWordEveryTurns(String username, String languageFrom, String languageTo, OrderType orderType, int numberOfExercises, int addingNewEveryX1) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        updateUserSettings.forUser(UserSettingsUpdateTO.newBuilder()
                .withUserId(user.id())
                .withFlagAsKnowWhenWinningStreakHitsX(5)
                .withInitialNumberOnPracticeWords(5)
                .withNewWordEveryXexcersises(addingNewEveryX1)
                .build());

        LanguagePairTO languagePairTO = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(IllegalAccessError::new);

        for(int i = 0; i < numberOfExercises; i++){
            PracticeWordTO practiceWordTO = practiceWords.nextWord(user.id(), languagePairTO.id(), orderType);

            System.out.println(practiceWordTO);

            practiceWords.practiced(user.id(), practiceWordTO.translationId(), practiceWordTO.reversed());
        }

        List<PracticeWordTO> practiceWordTOS = practiceWords.giveCurrentListToPractice(user.id(), languagePairTO.id(), orderType);

    }

    @And("^(.*) creates lesson with name '(.*)' for (.*)-(.*) aumatically with (\\d+) words from practice list$")
    public void createTestLessonWithNameTESTAumaticallyWithWordsFromPracticeList(String username, String name, String languageFrom, String languageTo, int numberOfWords) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        LanguagePairTO languagePair = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(() -> new IllegalStateException("Expected language pair for user"));


        NewAutomaticLessonTO newAutomaticLessonTO = NewAutomaticLessonTO.newBuilder()
                .withLanguagePairId(languagePair.id())
                .withLessonTitle(name)
                .withMaxNumberOfWords(numberOfWords)
                .withUserId(user.id())
                .build();
        createLesson.automaticallyFor(newAutomaticLessonTO);
        lessonRepository.findAll();
    }

    @And("^(.*) does test with name '(.*)' without making any mistake$")
    public void tomDoesTestWithNameTESTWithoutMakingAnyMistake(String username, String name) {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        ObjectId userId = new ObjectId(user.id());
        Lesson lesson = lessonRepository.findByUserIdAndName(userId, name).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));

        TestAssignmentTO testAssignmentTO = testForLesson.asSimpleTestForLesson(CreateTestTO.newBuilder()
                .withLessonId(lesson.getId().toString())
                .withOrderType(OrderType.RANDOM)
                .withUserId(user.id())
                .build());


        List<WordSolutionTO> solutions = testAssignmentTO.words().stream().map(word ->
                WordSolutionTO.newBuilder()
                        .withAnswerLanguage(word.anwserLanguage())
                        .withQuestion(word.question())
                        .withAnswer("een")
                        .withTranslationId(word.translationId())
                        .build())
                .collect(Collectors.toList());

        TestResultTO testResultTO = testForLesson.correctTest(TestSolutionTO.newBuilder()
                .withLessonId(testAssignmentTO.lessonId())
                .withSolutions(solutions)
                .build());
    }

    @And("^(.*) uploads a list (.*) for language pair (.*)-(.*)$")
    public void tomUploadsAListTranslationsNumbersCsvForLanguagePairNLFR(String username, String file, String languageFrom, String languageTo) throws Throwable {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        LanguagePairTO languagePair = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(() -> new IllegalStateException("Expected language pair for user"));

        registerTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(user.id())
                .withLanguagePairId(languagePair.id())
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/" + file)))
                .build());
    }
}

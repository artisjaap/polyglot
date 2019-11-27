package be.artisjaap.polyglot.cucumber;

import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWords;
import be.artisjaap.polyglot.core.action.lesson.SimpleNextWordStrategy;
import be.artisjaap.polyglot.core.action.lesson.TestForLesson;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.to.AnswerAndNextWordTO;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewAutomaticLessonTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewSimpleTranslationPairTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationForUserFromFileTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationsForUserTO;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordCheckTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.UserSettingsUpdateTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.action.to.test.CreateTestTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.action.to.test.TestAssignmentTO;
import be.artisjaap.polyglot.core.action.to.test.TestResultTO;
import be.artisjaap.polyglot.core.action.to.test.TestSolutionTO;
import be.artisjaap.polyglot.core.action.to.test.WordSolutionTO;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.user.FindUser;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.action.user.UpdateUserSettings;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.LessonRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PolyglotCoreStepsDefinition {
    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private CreateTranslation createTranslation;

    @Autowired
    private CreateLesson createLesson;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @Autowired
    private FindUser findUser;

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private FindPracticeWords findPracticeWords;

    @Autowired
    private UpdateUserSettings updateUserSettings;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TestForLesson testForLesson;

    @Autowired
    private SimpleNextWordStrategy simpleNextWordStrategy;

    @Given("^a user named (.*)$")
    public void eenGebruikerMetNaam(String naam) {

        registerUser.newUser(NewUserTO.newBuilder()
                .withUsername(naam)
                .withRole("ROLE_STUDENT")
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

        createTranslation.forAllWords(NewTranslationsForUserTO.newBuilder()
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
    public void zijnNietGekendeWoordenBegintTeOefenenMetInstellingen(String username, Map<String, String> settings) {
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
        OrderType orderType = languagePairTO.languageFrom().equalsIgnoreCase(languageFrom) ? OrderType.NORMAL : OrderType.REVERSE;
    }

    private Integer propertyAsInteger(Map<String, String> map, String key, String defaultValue) {
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

        for (int i = 0; i < numberOfExercises; i++) {
            PracticeWordTO practiceWordTO = findPracticeWords.nextWord(user.id(), languagePairTO.id(), orderType);

            System.out.println(practiceWordTO);

            findPracticeWords.practiced(user.id(), practiceWordTO.translationId(), practiceWordTO.reversed());
        }

        List<PracticeWordTO> practiceWordTOS = simpleNextWordStrategy.giveCurrentListToPractice(user.id(), languagePairTO.id(), orderType);

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
                .build());


        List<WordSolutionTO> solutions = testAssignmentTO.words().stream().map(word ->
                WordSolutionTO.newBuilder()
                        .withAnswerLanguage(word.anwserLanguage())
                        .withQuestion(word.question())
                        .withAnswer(word.answer())
                        .withTranslationId(word.translationId())
                        .build())
                .collect(Collectors.toList());

        TestResultTO testResultTO = testForLesson.correctTest(TestSolutionTO.newBuilder()
                .withLessonId(testAssignmentTO.lessonId())
                .withUserId(user.id())
                .withSolutions(solutions)
                .build());
    }

    @And("^(.*) uploads a list (.*) for language pair (.*)-(.*)$")
    public void tomUploadsAListTranslationsNumbersCsvForLanguagePairNLFR(String username, String file, String languageFrom, String languageTo) throws Throwable {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        LanguagePairTO languagePair = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(() -> new IllegalStateException("Expected language pair for user"));

        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(user.id())
                .withLanguagePairId(languagePair.id())
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/" + file)))
                .build());
    }

    @When("^(.*) practiced (\\d+) words for language pair (.*)-(.*)$")
    public void tomPracticedWords(String username, int numerOfPracticing, String languageFrom, String languageTo) throws Throwable {
        UserTO user = findUser.byUsername(username).orElseThrow(() -> new IllegalStateException("Verwacht dat user bestaat"));
        LanguagePairTO languagePair = findLanguagePair.pairForUser(user.id(), languageFrom, languageTo).orElseThrow(() -> new IllegalStateException("Expected language pair for user"));

        PracticeWordTO practiceWordTO = findPracticeWords.nextWord(user.id(), languagePair.id(), OrderType.NORMAL);
        for (int i = 0; i < numerOfPracticing; i++) {
            AnswerAndNextWordTO answerAndNextWordTO = findPracticeWords.checkAnswerAndGiveNext(PracticeWordCheckTO.newBuilder()
                    .withUserId(user.id())
                    .withTranslationId(practiceWordTO.translationId())
                    .withAnswerOrderType(OrderType.NORMAL)
                    .withAnswerGiven(practiceWordTO.answer())
                    .withNextOrderType(OrderType.NORMAL)
                    .build());
            practiceWordTO = answerAndNextWordTO.practiceWord();
        }

    }
}

package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.core.action.lesson.CreateNewWordForPractice;
import be.artisjaap.polyglot.core.action.pairs.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.Lesson;
import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.testhelper.LanguagePairPersister;
import be.artisjaap.polyglot.testhelper.LessonPersister;
import be.artisjaap.polyglot.testhelper.TranslationPracticePersister;
import be.artisjaap.polyglot.testhelper.UserPersister;
import be.artisjaap.polyglot.web.endpoints.old.LessonController;
import be.artisjaap.polyglot.web.endpoints.old.request.NewAutomaticLessonRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LessonControllerTest extends RestControllerTest {
    @Autowired
    private LessonController lessonController;

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @Autowired
    private CreateTranslation createTranslation;

    @Autowired
    private CreateNewWordForPractice createNewWordForPractice;

    @Autowired
    private TranslationPracticePersister translationPracticePersister;

    @Autowired
    private LanguagePairPersister languagePairPersister;

    @Autowired
    private UserPersister userPersister;

    @Autowired
    private LessonPersister lessonPersister;

    @Override
    protected MockMvc buildMocks() {
        return MockMvcBuilders.standaloneSetup(lessonController).build();
    }


    @Test
    public void autocreateLesson() throws Exception{
        User user = userPersister.randomUser();
        LanguagePair languagePair = languagePairPersister.randomForUser(user);
        translationPracticePersister.randomTranslationPracticeInKnowledgeStatus(languagePair, KnowledgeStatus.IN_PROGRESS, 100);

        mockMvc.perform(
                post("/api/lessons/autocreate")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(NewAutomaticLessonRequest.newBuilder()
                        .withLanguagePairId(languagePair.getId().toString())
                        .withLessonTitle("A new lesson")
                        .withMaxNumberOfWords(10)
                        .withUserId(user.getId().toString())
                        .build())))
                .andExpect(status().isOk())
               // .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo("A new lesson")))
                .andExpect(jsonPath("$.userId", equalTo(user.getId().toString())))
                .andExpect(jsonPath("$.translations", hasSize(10)))
                ;

    }

    @Test
    public void practiceLesson() throws Exception {
        Lesson lesson = lessonPersister.randomLesson(10);

        mockMvc.perform(
          get("/api/lessons/practice/" + lesson.getId()))
                  .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo(lesson.getName())))
                .andExpect(jsonPath("$.userId", equalTo(lesson.getUserId().toString())))
                .andExpect(jsonPath("$.translations", hasSize(10)))
        ;

    }

    @Test
    public void testLesson() throws Exception {
        Lesson lesson = lessonPersister.randomLesson(10);

        mockMvc.perform(
                get("/api/lessons/test/" + lesson.getId() + "/" + NORMAL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
//                .andExpect(content().string(""))
                .andExpect(jsonPath("$.lessonName", equalTo(lesson.getName())))
                .andExpect(jsonPath("$.words", hasSize(10)))
        ;

    }


//    @RequestMapping(value = "/test/{userId}/{lessonId}/{orderType}", method = RequestMethod.GET)
//    @RequestMapping(value = "/test/correct", method = RequestMethod.POST)

}

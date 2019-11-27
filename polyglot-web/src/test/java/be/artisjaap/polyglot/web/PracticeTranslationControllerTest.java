package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.testhelper.LanguagePairPersister;
import be.artisjaap.polyglot.testhelper.TranslationPracticePersister;
import be.artisjaap.polyglot.testhelper.UserPersister;
import be.artisjaap.polyglot.web.endpoints.old.PracticeTranslationController;
import be.artisjaap.polyglot.web.endpoints.old.request.TranslationsFilterRequest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PracticeTranslationControllerTest extends RestControllerTest {

    @Autowired
    private PracticeTranslationController practiceTranslationController;

    @Autowired
    private UserPersister userPersister;

    @Autowired
    private LanguagePairPersister languagePairPersister;

    @Autowired
    private TranslationPracticePersister translationPracticePersister;

    @Override
    protected MockMvc buildMocks() {
        return MockMvcBuilders.standaloneSetup(practiceTranslationController).build();
    }

    @Test
    public void testFilteredDataFirstPage() throws Exception {
        User user = userPersister.randomUser();
        LanguagePair languagePair = languagePairPersister.randomForUser(user);
        translationPracticePersister.randomTranslationPracticeInKnowledgeStatus(languagePair, KnowledgeStatus.IN_PROGRESS, 100);

        mockMvc.perform(
                post("/api/translations/practice/list/all/filterd")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(TranslationsFilterRequest
                        .newBuilder().withLanguagePairId(languagePair.getId().toString())
                        .withPageNumber(0)
                        .withPageSize(10)
                        .build())))
                .andExpect(status().isOk())
//                    .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", equalTo(0)))
                .andExpect(jsonPath("$.pageSize", equalTo(10)))
                .andExpect(jsonPath("$.numberOfPages", equalTo(10)))
                .andExpect(jsonPath("$.lastPage", equalTo(false)))
                .andExpect(jsonPath("$.data", hasSize(10)))
        ;
    }

    @Ignore
    public void testFilteredDataLastPage() throws Exception {
        User user = userPersister.randomUser();
        LanguagePair languagePair = languagePairPersister.randomForUser(user);
        translationPracticePersister.randomTranslationPracticeInKnowledgeStatus(languagePair, KnowledgeStatus.IN_PROGRESS, 100);

        mockMvc.perform(
                post("/api/translations/practice/list/all/filterd")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(TranslationsFilterRequest
                        .newBuilder()
                        .withLanguagePairId(languagePair.getId().toString())
                        .withPageNumber(0)
                        .withTextFilter("is")
                        .withPageSize(10).build())))
                .andExpect(status().isOk())
//                    .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", equalTo(0)))
                .andExpect(jsonPath("$.pageSize", equalTo(10)))
                .andExpect(jsonPath("$.numberOfPages", equalTo(1)))
                .andExpect(jsonPath("$.lastPage", equalTo(true)))
                .andExpect(jsonPath("$.data", hasSize(10)))
        ;
    }
}

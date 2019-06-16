package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.core.action.pairs.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.web.endpoints.ManageTranslationController;
import be.artisjaap.polyglot.web.endpoints.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.request.NewSimpleTranslationRequest;
import be.artisjaap.polyglot.web.endpoints.request.NewTranslationsForUserRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ManageTranslationControllerTest extends RestControllerTest {

    @Autowired
    private ManageTranslationController manageTranslationController;

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @Override
    protected MockMvc buildMocks() {
        return MockMvcBuilders.standaloneSetup(manageTranslationController).build();
    }

    @Test
    public void findLanguagePairs() {
        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("test").withPassword("password").build());
        registerLanguagePair.forUser(NewLanguagePairTO.newBuilder().withUserId(userTO.id()).withLanguageFrom("FROM").withLanguageTo("TO").build());
        try {
            mockMvc.perform(
                    get("/api/translations/pairs/" + userTO.id()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$[0].languageFrom", equalTo("FROM")))
                    .andExpect(jsonPath("$[0].languageTo", equalTo("TO")))
                    .andExpect(jsonPath("$[0].turnsDone", equalTo(0)))
                    .andExpect(jsonPath("$[0].turnsDoneReverse", equalTo(0)))
                    .andExpect(jsonPath("$[0].lastTurn", isEmptyOrNullString()))
                    .andExpect(jsonPath("$[0].lastTurnReverse", isEmptyOrNullString()))
            ;

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void createNewLanguagePair() throws Exception {
        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("test").withPassword("password").build());

        LanguagePairRequest languagePair = LanguagePairRequest.newBuilder().withLanguageFrom("FROM")
                .withLanguageTo("TO")
                .withUserId(userTO.id()).build();

        mockMvc.perform(
                post("/api/translations/pairs")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(languagePair)))
                .andExpect(status().isOk())
                //   .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

    }


    @Test
    public void createTranslationsForLanguagePair() throws Exception {
        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("test").withPassword("password").build());
        LanguagePairTO languagePairTO = registerLanguagePair.forUser(NewLanguagePairTO.newBuilder().withUserId(userTO.id())
                .withLanguageFrom("FROM")
                .withLanguageTo("TO")
                .build());
        List<NewSimpleTranslationRequest> translationRequestList = Arrays.asList(NewSimpleTranslationRequest.newBuilder().withLanguageFrom("one").withLanguageTO("een").build(),
                NewSimpleTranslationRequest.newBuilder().withLanguageFrom("two").withLanguageTO("twee").build());


        NewTranslationsForUserRequest translations = NewTranslationsForUserRequest.newBuilder()
                .withLanguagePairId(languagePairTO.id())
                .withUserId(userTO.id())
                .withTranslations(translationRequestList)
                .build();

        mockMvc.perform(
                post("/api/translations/pairs/translations")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(translations)))
                .andExpect(status().isOk())
//                   .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

    }

}



package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.core.action.RegisterUser;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.web.endpoints.ManageTranslationController;
import be.artisjaap.polyglot.web.endpoints.request.LanguagePairRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ManageTranslationControllerTest extends RestControllerTest {

    @Autowired
    private ManageTranslationController manageTranslationController;

    @Autowired
    private RegisterUser registerUser;

    @Override
    protected MockMvc buildMocks() {
        return MockMvcBuilders.standaloneSetup(manageTranslationController).build();
    }

    @Test
    public void findLanguagePairs() {
        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("test").withPassword("password").build());
        try {
            mockMvc.perform(
                    get("/api/translations/pairs/" + userTO.id()))
                    .andExpect(status().isOk())
//                    .andExpect(content().string(""))
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
//                    .andExpect(jsonPath("$.id", not(isEmptyString())))
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

}



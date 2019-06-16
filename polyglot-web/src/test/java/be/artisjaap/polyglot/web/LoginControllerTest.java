package be.artisjaap.polyglot.web;

import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.web.endpoints.LoginController;
import be.artisjaap.polyglot.web.endpoints.request.NewUserRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest extends RestControllerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private RegisterUser registerUser;

    @Override
    protected MockMvc buildMocks() {
        return MockMvcBuilders.standaloneSetup(loginController).build();
    }


    @Test
    public void createUser() throws Exception{
        mockMvc.perform(
                post("/public/api/register")
                        .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(NewUserRequest.newBuilder().withUsername("Tom").withPassword("secret").build())))
                .andExpect(status().isOk())
//                    .andExpect(content().string(""))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username", equalTo("Tom")))
                .andExpect(jsonPath("$.userId", not(empty())))
                .andExpect(jsonPath("$.token", not(empty())))
        ;
    }

    @Test
    public void loginUser() throws Exception {
        registerUser.newUser(NewUserTO.newBuilder().withUsername("Tom").withPassword("secret").build());

        mockMvc.perform(
                get("/public/api/login/Tom/secret"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username", equalTo("Tom")))
                .andExpect(jsonPath("$.userId", not(empty())))
                .andExpect(jsonPath("$.token", not(empty())))
        ;

    }


}

package be.artisjaap.common.cucumber;

import be.artisjaap.common.action.SetContext;
import be.artisjaap.common.action.vo.UserDataVO;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonStepDefinition {

    @Autowired
    private SetContext setContext;


    @Given("^A user named (.*)$")
    public void setUserName(String name){
        setContext.with(UserDataVO.newBuilder().withUserId(name).build());

    }
}

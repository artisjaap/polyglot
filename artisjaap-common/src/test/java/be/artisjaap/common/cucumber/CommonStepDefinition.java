package be.artisjaap.common.cucumber;

import be.artisjaap.common.action.SetContext;
import be.artisjaap.common.action.vo.UserDataVO;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommonStepDefinition {

    @Autowired
    private SetContext setContext;


    @Given("^A user named (.*)$")
    public void setUserName(String name) {
        setContext.with(UserDataVO.builder().userId(name).build());

    }

    @ParameterType(".*?")
    public List<String> stringList(String string){
        return new ArrayList<>();
    }
    @ParameterType(".*?")
    public String data(String string){
        return string;
    }
}

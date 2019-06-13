package be.artisjaap.schedule.cucumber;

import be.artisjaap.common.action.SetContext;
import be.artisjaap.common.action.vo.UserDataVO;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
public class CucumberContext extends ScheduleInMemoryTestParent {
    @Autowired
    private SetContext setContext;

    @Before
    public void init() {
        setContext.with(UserDataVO.newBuilder().withUserId("TESTUSER").build());
    }
}

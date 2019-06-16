package be.artisjaap.common.action;

import be.artisjaap.common.action.vo.UserDataVO;
import be.artisjaap.common.service.ContextManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetContext {
    @Autowired
    private ContextManager contextManager;

    public void with(UserDataVO userData){
        contextManager.setUser(userData);
    }
}

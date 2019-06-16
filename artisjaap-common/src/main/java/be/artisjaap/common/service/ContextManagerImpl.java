package be.artisjaap.common.service;

import be.artisjaap.common.action.vo.UserDataVO;
import org.springframework.stereotype.Component;

@Component
public class ContextManagerImpl implements ContextManager {

    private static ThreadLocal<UserDataVO> sessionContext = new ThreadLocal<>();

    public ContextManagerImpl() {
        Context.init(this);
    }

    @Override
    public void setUser(UserDataVO user) {
        ContextManagerImpl.sessionContext.set(user);
    }

    @Override
    public UserDataVO getUser() {
        return sessionContext.get();
    }

}

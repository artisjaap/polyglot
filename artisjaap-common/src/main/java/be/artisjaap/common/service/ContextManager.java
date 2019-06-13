package be.artisjaap.common.service;

import be.artisjaap.common.action.vo.UserDataVO;

public interface ContextManager {

    void setUser(UserDataVO user);

    UserDataVO getUser();
}

package be.artisjaap.common.service;

import be.artisjaap.common.action.vo.UserDataVO;

import java.util.Optional;

public class Context {

    private static ContextManager contextManager;

    protected static void init(ContextManager cm) {
        contextManager = cm;
    }

    public static String userId() {
        return user().getUserId();
    }

    public static UserDataVO user() {
        return Optional.ofNullable(contextManager)
                .map(ContextManager::getUser)
                .orElseGet(() -> UserDataVO.anonymous());

    }
}

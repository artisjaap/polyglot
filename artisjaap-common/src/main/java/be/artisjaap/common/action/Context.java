package be.artisjaap.common.action;

import be.artisjaap.common.action.vo.UserDataVO;

public class Context {

    private static UserDataVO userDataVO = UserDataVO.newBuilder().withUserId("ANONYMOUS").build();

    public static UserDataVO userDataVO(){
            return userDataVO;
    }

    public static String userId() {
        return userDataVO.getUserId();
    }
}

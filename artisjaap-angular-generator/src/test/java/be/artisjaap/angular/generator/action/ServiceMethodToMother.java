package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ServiceMethodToMother {

    public static ServiceMethodVO init(){
        return ServiceMethodVO.newBuilder()
                .withBodyType("BodyType")
                .withEndpoint("/api/testurl/{param1}/{param2}/{param3}")
                .withMethodName("method")
                .withRequestMethod(RequestMethod.GET)
                .withRequiredClasses(new HashSet<>())
                .withReturnsList(true)
                .withReturnType("List")
                .withUrlParameters(UrlParameterToMother.initNumberOfParams(3))
                .build();
    }
}

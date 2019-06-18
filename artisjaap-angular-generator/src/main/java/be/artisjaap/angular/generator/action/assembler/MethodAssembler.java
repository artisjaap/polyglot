package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import be.artisjaap.common.action.Assembler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Component
public class MethodAssembler implements Assembler<ServiceMethodVO, Method> {
    @Override
    public ServiceMethodVO assembleTO(Method method) {
        return ServiceMethodVO.newBuilder()
                .withBodyType(extractBodyType(method))
                .withEndpoint()
                .withMethodName()
                .withRequestMethod()
                .withReturnsList()
                .withReturnType()
                .withUrlParameters()
                .build();
    }

    private String extractBodyType(Method method) {
        //@RequestBody JournalReportRequest reportRequest
        RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        if (requestMapping.method().)
    }

    private findMethodParameterWithRequestBody(Method method) {
        method.getParameters()[0].
    }

    @Override
    public Method assembleEntity(ServiceMethodVO serviceMethodVO) {
        throw new UnsupportedOperationException("");
    }
}

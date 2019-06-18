package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import be.artisjaap.common.action.Assembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class MethodAssembler implements Assembler<ServiceMethodVO, Method> {
    @Override
    public ServiceMethodVO assembleTO(Method method) {
        return ServiceMethodVO.newBuilder()
                .withBodyType(findMethodParameterWithRequestBody(method))
                .withEndpoint(findEndpoint(method))
                .withMethodName(method.getName())
                .withRequestMethod(findRequestMethod(method))
                .withReturnsList(returnTypeIsList(method))
                .withReturnType(methodReturnType(method).getSimpleName())
            //    .withUrlParameters()
                .build();
    }




    private String findMethodParameterWithRequestBody(Method method) {
        return ReflectionUtils.findParameterWithAnnotation(method, RequestBody.class).map(Parameter::getType).map(Class::getSimpleName).orElse(null);
    }

    private RequestMethod findRequestMethod(Method method){

        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        RequestMethod[] methods = annotation.method();
        if(methods.length == 0){
            throw new IllegalStateException();
        }

        return methods[0];
    }

    private String findEndpoint(Method method){
        RequestMapping requestMappingClass = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        RequestMapping requestMappingMethod = method.getAnnotation(RequestMapping.class);

        String path = firstPathFromRequestMapping(requestMappingClass);
        String path2 = firstPathFromRequestMapping(requestMappingMethod);

        return path + path2;

    }

    private boolean returnTypeIsList(Method method){
        Class<?> clazz = methodReturnType((method));
        return (Collection.class.isAssignableFrom(clazz));
    }

    private Class<?> methodReturnType(Method method) {
        Type returnType = method.getGenericReturnType();
        if( ParameterizedType.class.isAssignableFrom(returnType.getClass())){

            ParameterizedType type = (ParameterizedType) returnType;

//            if(ResponseEntity.class.isAssignableFrom(type.getRawType().getClass()))

            Type[] typeArguments = type.getActualTypeArguments();
            Class<?> aClass = null;
            try {
                aClass = Class.forName(typeArguments[0].getTypeName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return aClass;
            //return typeArguments[0].getTypeName().getClass();
//            for(Type typeArgument : typeArguments){
//                Class typeArgClass = (Class) typeArgument;
//                System.out.println("typeArgClass = " + typeArgClass);
//            }
        }

        return Object.class;
    }

    @Override
    public Method assembleEntity(ServiceMethodVO serviceMethodVO) {
        throw new UnsupportedOperationException("");
    }

    private String firstPathFromRequestMapping(RequestMapping requestMapping){
        String[] strings = Optional.ofNullable(requestMapping).map(RequestMapping::value).orElse(new String[]{""});
        String path = (strings.length > 0) ? strings[0] : "";
        return path;
    }
}

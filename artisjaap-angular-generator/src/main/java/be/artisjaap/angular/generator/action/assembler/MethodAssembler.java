package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import be.artisjaap.angular.generator.action.vo.UrlParameterVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import be.artisjaap.common.action.Assembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MethodAssembler implements Assembler<ServiceMethodVO, Method> {
    private static final Logger logger = LoggerFactory.getLogger(MethodAssembler.class);

    @Autowired
    private UrlParameterAssembler urlParameterAssembler;

    @Override
    public ServiceMethodVO assembleTO(Method method) {
        return ServiceMethodVO.newBuilder()
                .withBodyType(findMethodParameterWithRequestBody(method).map(Class::getSimpleName).orElse(null))
                .withEndpoint(findEndpoint(method))
                .withMethodName(method.getName())
                .withRequestMethod(findRequestMethod(method))
                .withReturnsList(returnTypeIsList(method))
                .withReturnType(methodReturnType(method).getSimpleName())
                .withRequiredClasses(addRequiredClasses(method))
                .withUrlParameters(addUrlParameters(method))
                .build();
    }

    private List<UrlParameterVO> addUrlParameters(Method method) {
        //list vars in path
        List<String> endpointVars = extractVars(findEndpoint(method));
        //find corresponding pahtvariables
        List<UrlParameterVO> parameters = urlParameterAssembler.assembleTOs(findPathVariables(method));
        //order in the same order as path vars
        return orderParameters(endpointVars, parameters);
    }

    private List<UrlParameterVO> orderParameters(List<String> endpointVars, List<UrlParameterVO> parameters) {
        if (endpointVars.size() != parameters.size()) {
            throw new IllegalStateException("Endpoint vars don't match method parameters");
        }
        Map<String, UrlParameterVO> collect = parameters.stream().collect(Collectors.toMap(UrlParameterVO::getName, Function.identity()));

        return endpointVars.stream().map(key -> collect.get(key)).collect(Collectors.toList());
    }

    private List<Parameter> findPathVariables(Method method) {
        return ReflectionUtils.findParametersWithAnnotation(method, PathVariable.class);
    }

    private List<String> extractVars(String endpoint) {
        List<String> vars = new ArrayList<>();
        Pattern compile = Pattern.compile("\\{([a-zA-Z]+)}");
        Matcher matcher = compile.matcher(endpoint);

        while (matcher.find()) {
            vars.add(matcher.group(1));
        }

        return vars;

    }

    private Set<Class<?>> addRequiredClasses(Method method) {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(methodReturnType(method));
        findMethodParameterWithRequestBody(method).ifPresent(classes::add);

        return classes;
    }


    private Optional<Class<?>> findMethodParameterWithRequestBody(Method method) {
        return ReflectionUtils.findParameterWithAnnotation(method, RequestBody.class).map(Parameter::getType);
    }

    private RequestMethod findRequestMethod(Method method) {
        RequestMapping annotation = ReflectionUtils.findAnnotationOnMethod(method, RequestMapping.class)
                .orElseThrow(() -> new IllegalStateException(String.format("method [%s] should have RequestMapping annotation", method.getName())));
        RequestMethod[] methods = annotation.method();
        if (methods.length == 0) {
            throw new IllegalStateException();
        }
        if (methods.length > 1) {
            logger.warn("More than one method HTTP Method find on method %s", method.getName());
        }
        return methods[0];
    }

    private String findEndpoint(Method method) {
        Optional<RequestMapping> requestMappingClass = ReflectionUtils.findAnnotationOnClass(method.getDeclaringClass(), RequestMapping.class);
        Optional<RequestMapping> requestMappingMethod = ReflectionUtils.findAnnotationOnMethod(method, RequestMapping.class);

        String path = requestMappingClass.map(this::firstPathFromRequestMapping).orElse("");
        String path2 = requestMappingMethod.map(this::firstPathFromRequestMapping).orElse("");

        return path + path2;
    }

    private boolean returnTypeIsList(Method method) {
        Class<?> clazz = methodReturnType((method));
        return (Collection.class.isAssignableFrom(clazz));
    }

    private Class<?> methodReturnType(Method method) {
        Type returnType = method.getGenericReturnType();
        Class<?> returnTypeClass = method.getReturnType();
        if (returnTypeClass != null && !ResponseEntity.class.isAssignableFrom(returnTypeClass)) {
            logger.error(String.format("Returntype [%s] is not assignable to ResponseEntity", returnTypeClass.getSimpleName()));
            return Object.class;
        }

        if (ParameterizedType.class.isAssignableFrom(returnType.getClass())) {
            ParameterizedType type = (ParameterizedType) returnType;

            Type[] typeArguments = type.getActualTypeArguments();
            if (typeArguments.length != 1) {
                logger.error("Invalid number of generic types found for returntype of method [%s]", method.getName());
            }

            try {
                return Class.forName(typeArguments[0].getTypeName());
            } catch (ClassNotFoundException e) {
                logger.error(String.format("Returntype class not found"));
            }
        }

        logger.error(String.format("Something went wrong in finding methodReturnType"));


        return Object.class;
    }

    @Override
    public Method assembleEntity(ServiceMethodVO serviceMethodVO) {
        throw new UnsupportedOperationException("");
    }

    private String firstPathFromRequestMapping(RequestMapping requestMapping) {
        String[] strings = Optional.ofNullable(requestMapping).map(RequestMapping::value).orElse(new String[]{""});
        String path = (strings.length > 0) ? strings[0] : "";
        return path;
    }
}

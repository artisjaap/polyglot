package be.artisjaap.angular.generator.utils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static boolean methodHasAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }


    public static Optional<Parameter> findParameterWithAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getParameters()).stream().filter(p -> parameterHasAnnotation(p, annotationClass)).findFirst();
    }

    public static <T extends Annotation> Optional<T> findAnnotationOnMethod(Method method, Class<T> annotationClass){
        return Optional.ofNullable(method.getAnnotation(annotationClass));
    }

    public static <T extends Annotation> Optional<T> findAnnotationOnClass(Class<?> clazz, Class<T> annotationClass){
        return Optional.ofNullable(clazz.getAnnotation(annotationClass));
    }

    public static <T extends Annotation> Optional<T> findAnnotationOnParameter(Parameter parameter, Class<T> annotationClass){
        return Optional.ofNullable(parameter.getAnnotation(annotationClass));
    }

    private static boolean parameterHasAnnotation(Parameter p, Class<?> annotationClass) {
        return Arrays.asList(p.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }


    public static List<Parameter> findParametersWithAnnotation(Method method, Class<PathVariable> annotationClass) {
        return Arrays.asList(method.getParameters()).stream().filter(p -> parameterHasAnnotation(p, annotationClass)).collect(Collectors.toList());
    }
}

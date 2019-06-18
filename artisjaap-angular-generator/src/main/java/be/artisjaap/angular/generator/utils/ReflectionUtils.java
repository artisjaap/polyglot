package be.artisjaap.angular.generator.utils;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

public class ReflectionUtils {

    public static boolean methodHasAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }


    public static Optional<Parameter> findParameterWithAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getParameters()).stream().filter(p -> parameterHasAnnotation(p, annotationClass)).findFirst();
    }



    private static boolean parameterHasAnnotation(Parameter p, Class<?> annotationClass) {
        return Arrays.asList(p.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }



}

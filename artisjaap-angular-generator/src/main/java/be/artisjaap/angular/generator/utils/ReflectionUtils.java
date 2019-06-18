package be.artisjaap.angular.generator.utils;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ReflectionUtils {

    public static boolean methodHasAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }

    public static <T extends Annotation> Optional<T> getAnnotation(Method method, Class<T> annotationClass){
        T[] annotationsByType = method.getAnnotationsByType(annotationClass);



    }

}

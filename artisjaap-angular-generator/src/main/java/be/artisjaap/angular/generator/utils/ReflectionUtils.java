package be.artisjaap.angular.generator.utils;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtils {

    public static boolean methodHasAnnotation(Method method, Class<?> annotationClass){
        return Arrays.asList(method.getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }
}

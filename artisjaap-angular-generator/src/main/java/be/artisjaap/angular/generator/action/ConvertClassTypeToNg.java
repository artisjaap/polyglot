package be.artisjaap.angular.generator.action;


import be.artisjaap.angular.generator.utils.ReflectionUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConvertClassTypeToNg {

    private static Map<Class, String> classLookup = new HashMap<>();

    static {
        classLookup.put(String.class, "string");
        classLookup.put(Integer.class, "number");
        classLookup.put(Long.class, "number");
        classLookup.put(Double.class, "number");
        classLookup.put(Float.class, "number");
    }

    public static boolean isNativeType(Class<?> clazz){
        return classLookup.containsKey(clazz);
    }

    public static boolean isList(Class<?> clazz){
        return Collection.class.isAssignableFrom(clazz);
    }

    public static String toNgType(Class<?> clazz){
        if(isNativeType(clazz)){
            return classLookup.get(clazz);
        }
        return clazz.getSimpleName();

    }

    public static Class<?> realReturnType(Type type){
        return ReflectionUtils.realClassForReturnType(type);
    }

    public static String convertToNgType(Type type) {
        if(isList(type.getClass())){
            return toNgType(realReturnType(type));
        }
        return toNgType(type.getClass());
    }

    public static String convertToNgTypeForField(Class type) {
        if(isList(type)){
            return toNgType(realReturnType(type));
        }
        return toNgType(type);
    }
}

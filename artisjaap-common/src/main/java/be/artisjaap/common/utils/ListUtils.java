package be.artisjaap.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListUtils {

    private ListUtils(){}

    public static <T> List<T> shuffle(List<T> list) {
        List<T> randomList = new ArrayList<>(list);
        for (int i = 0; i < list.size() * 2; i++) {
            randomList = moveRandomElement(list);
        }
        return randomList;
    }

    public static <T> T getRandomElement(List<T> list) {
        if(list.isEmpty()){
            throw new IllegalStateException("empty list");
        }
        return shuffle(list).iterator().next();
    }

    public static <T> T getRandomElement(List<T> list, double chanceOnFirstElement) {
        if(list.isEmpty()){
            throw new IllegalStateException("empty list");
        }
        if(list.size() == 1){
            return list.iterator().next();
        }
        
        if(Math.random() < chanceOnFirstElement){
            return list.iterator().next();
        }
        return shuffle(list.subList(1, list.size())).iterator().next();
    }

    private static <T> List<T> moveRandomElement(List<T> list) {
        int index = Double.valueOf(Math.floor(Math.random() * list.size())).intValue();
        T element = list.remove(index);
        list.add(element);
        return list;
    }

    public static <T> T getRandomFromCollection(Collection<T> collection){
        return collection.iterator().next();
    }
}

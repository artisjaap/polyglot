package be.artisjaap.common.utils;

import java.util.ArrayList;
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

    private static <T> List<T> moveRandomElement(List<T> list) {
        int index = Double.valueOf(Math.floor(Math.random() * list.size())).intValue();
        T element = list.remove(index);
        list.add(element);
        return list;
    }
}

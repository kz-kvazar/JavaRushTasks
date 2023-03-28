package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, E extends Convertable<K>>Map<K, E> convert(List<E> list) {
        Map<K, E> result = new HashMap<>(list.size());
        for (E element : list) {
            K key = element.getKey();
            result.put(key,element);
        }
        return result;
    }
}

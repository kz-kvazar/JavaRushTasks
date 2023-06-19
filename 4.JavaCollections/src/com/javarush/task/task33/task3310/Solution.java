package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.FileStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        long elementsNumber = 10000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) throws IOException {
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");

        Set<String> originalStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; ++i) {
            originalStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener, originalStrings);
        Date stop = new Date();
        String result = String.valueOf(stop.getTime() - start.getTime());
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + result);

        start = new Date();
        Set<String> strings = getStrings(shortener, ids);
        stop = new Date();
        result = String.valueOf(stop.getTime() - start.getTime());
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + result);

        if (originalStrings.equals(strings)){
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
        Helper.printMessage("");
    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) throws IOException {
        Set<Long> set = new HashSet<>();
        for (String s : strings) {
            set.add(shortener.getId(s));
        }
        return set;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> set = new HashSet<>();
        for (Long n : keys) {
            set.add(shortener.getString(n));
        }
        return  set;
    }
}

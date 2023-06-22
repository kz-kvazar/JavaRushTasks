package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long start = new Date().getTime();
        for (String s : strings) {
            try {
                ids.add(shortener.getId(s));
            } catch (IOException ignored) {
            }
        }
        long stop = new Date().getTime();
        return stop - start;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long start = new Date().getTime();
        for (Long l : ids) {
            strings.add(shortener.getString(l));
        }
        long stop = new Date().getTime();
        return stop - start;
    }

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);
        Set<String> origStrings = new HashSet<>();


        for (int i = 0; i < 10_000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        HashSet<Long> s1ids = new HashSet<>();
        long s1Time = getTimeToGetIds(shortener1, origStrings, s1ids);

        HashSet<Long> s2ids = new HashSet<>();
        long s2Time = getTimeToGetIds(shortener2, origStrings, s2ids);

        Assert.assertTrue(s1Time > s2Time);

        long s3Time = getTimeToGetStrings(shortener1, s1ids, new HashSet<>());
        long s4Time = getTimeToGetStrings(shortener2, s2ids, new HashSet<>());

        Assert.assertEquals(s3Time,s4Time,30);


    }
}

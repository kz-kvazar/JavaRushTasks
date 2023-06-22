package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String str1 = Helper.generateRandomString();
        String str2 = Helper.generateRandomString();
        String str3 = str1;
        try {
            Long id1 = shortener.getId(str1);
            Long id2 = shortener.getId(str2);
            Long id3 = shortener.getId(str3);
            Assert.assertNotEquals(id2, id1);
            Assert.assertNotEquals(id2, id3);
            Assert.assertEquals(id1,id3);
            String s1 = shortener.getString(id1);
            String s2 = shortener.getString(id2);
            String s3 = shortener.getString(id3);
            Assert.assertEquals(s1, str1);
            Assert.assertEquals(s2, str2);
            Assert.assertEquals(s3, str3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        try {
            FileStorageStrategy strategy = new FileStorageStrategy();
            Shortener shortener = new Shortener(strategy);
            testStorage(shortener);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}

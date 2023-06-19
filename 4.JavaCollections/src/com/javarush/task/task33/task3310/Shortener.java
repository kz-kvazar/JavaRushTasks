package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.io.IOException;

public class Shortener {
    private Long lastId = 0L;
    private final StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) throws IOException {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        }
        lastId++;
        storageStrategy.put(lastId, string);
        return lastId;
        
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}

package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;

public interface StorageStrategy {

    public boolean containsKey(Long key);
    public boolean containsValue(String value);
    public void put(Long key,String value) throws IOException;
    public Long getKey(String value);
    public String getValue(Long key);

}

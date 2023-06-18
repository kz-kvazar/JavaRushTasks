package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    public int hashCode(){
        return key.hashCode() ^ value.hashCode();
    }
    public String toString(){
        return key + "=" + value;
    }
    public boolean equals(Object entry) {
        if ( this == entry) return true;
        if (entry == null || this.getClass() != entry.getClass()) return false;
        Entry e = (Entry) entry;
        
        if(!Objects.equals(this.key, e.key)) return false;
        return Objects.equals(value, e.value);
    }
}

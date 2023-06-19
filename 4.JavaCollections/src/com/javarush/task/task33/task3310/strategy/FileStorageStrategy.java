package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

    FileBucket[] table;
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public FileStorageStrategy() throws IOException {
        init();
    }

    private void init() throws IOException {
        table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    final Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }

        int index = indexFor(key.hashCode(), table.length);
        Entry entry = table[index].getEntry();
        while (entry != null) {
            if (key.equals(entry.key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    public void put(Long key, String value) throws IOException {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry e = table[index].getEntry();
        while (e != null) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
            e = e.next;
        }
        addEntry(hash, key, value, index);
    }

    void resize(int newCapacity) throws IOException {
        FileBucket[] newTable = new FileBucket[newCapacity];

        for (int i = 0; i < newTable.length; i++)
            newTable[i] = new FileBucket();

        transfer(newTable);

        for (FileBucket fileBucket : table) fileBucket.remove();

        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        maxBucketSize = 0;

        for (FileBucket fileBucket : table) {
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int indexInNewTable = indexFor(entry.getKey().hashCode(), newCapacity);
                entry.next = newTable[indexInNewTable].getEntry();
                newTable[indexInNewTable].putEntry(entry);
                entry = next;
            }

            long currentBucketSize = fileBucket.getFileSize();
            if (currentBucketSize > maxBucketSize)
                maxBucketSize = currentBucketSize;
        }
    }

    public boolean containsValue(String value) {
        for (FileBucket tableElement : table)
            for (Entry e = tableElement.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null)
            return entry.getValue();

        return null;
    }

    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    public Long getKey(String value) {
        for (FileBucket tableElement : table)
            for (Entry e = tableElement.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return e.getKey();
        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) throws IOException {
        if ((maxBucketSize > bucketSizeLimit)) {
            resize(2 * table.length);
            bucketIndex = indexFor(key.hashCode(), table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;

        long currentBucketSize = table[bucketIndex].getFileSize();
        if (currentBucketSize > maxBucketSize)
            maxBucketSize = currentBucketSize;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}


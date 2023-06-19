package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() throws IOException {
        path = Files.createTempFile(null,null);
        Files.deleteIfExists(path);
        Files.createFile(path);
        path.toFile().deleteOnExit();
    }
    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void putEntry(Entry entry){
        try (ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(path))){
            writer.writeObject(entry);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry(){
        if (getFileSize() > 0){
            try (ObjectInputStream reader = new ObjectInputStream(Files.newInputStream(path))){
                return (Entry) reader.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;

    }
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

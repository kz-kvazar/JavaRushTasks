package com.javarush.task.task20.task2022;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName =fileName;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution sol = new Solution("fileName");
        sol.writeObject("hello world");
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("file")));
        oos.writeObject(sol);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("file")));
        Solution sol2 = (Solution) ois.readObject();
        sol2.writeObject("my name is...");

        BufferedReader reader = new BufferedReader(new FileReader("fileName"));
        while (reader.ready()){
            System.out.println(reader.readLine());
        }
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
}

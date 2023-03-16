package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")){
            long number = Long.parseLong(args[1]);
            String text = args[2];
            int textLength = text.length();

            raf.seek(number);
            byte[] bytes = new byte[textLength];
            raf.read(bytes,0,textLength);

            String readeText = new String(bytes);

            raf.seek(raf.length());
            if (readeText.equals(text)){
                
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

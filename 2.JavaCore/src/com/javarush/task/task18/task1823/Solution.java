package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String result;
        while (!(result = reader.readLine()).equals("exit")) {
            new ReadThread(result);
        }

    }

    public static class ReadThread extends Thread {
        private final String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
            start();
            //implement constructor body
        }

        @Override
        public void run() {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                byte[] bytes = new byte[256];
                while (fileInputStream.available() > 0) {
                    bytes[fileInputStream.read()]++;
                }
                int maxValue = 0;
                int maxCount = 0;
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] > maxValue) {
                        maxValue = bytes[i];
                        maxCount = i;
                    }
                }
                resultMap.put(fileName, maxCount);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
// implement file reading here - реализуйте чтение из файла тут
    }
}

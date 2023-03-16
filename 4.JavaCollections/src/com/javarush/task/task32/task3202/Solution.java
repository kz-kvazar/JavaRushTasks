package com.javarush.task.task32.task3202;

import java.io.*;
import java.util.Arrays;

/* 
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {

                while (bufferedReader.ready()) {
                    stringWriter.write(bufferedReader.readLine());
                }
            }
        }
        return stringWriter;

    }
}

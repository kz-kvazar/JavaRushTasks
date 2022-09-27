package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //BufferedWriter br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        try (FileInputStream file1InputStream = new FileInputStream(fileName1);
             FileInputStream file2InputStream = new FileInputStream(fileName2);
             FileOutputStream file1OutputStream = new FileOutputStream(fileName1)) {
            while (file1InputStream.available() > 0) {
                byteArrayOutputStream.write(file1InputStream.read());
            }
            while (file2InputStream.available() > 0) {
                file1OutputStream.write(file2InputStream.read());
            }
          // file1OutputStream.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.writeTo(file1OutputStream);
        }
    }
}

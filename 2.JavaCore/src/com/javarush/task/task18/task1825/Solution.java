package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName ;
        Set<String> names = new TreeSet<>() ;
        while (!(fileName = reader.readLine()).equals("end")) {
            names.add(fileName);
        }
        for (String s : names) {
            String path = s.substring(0, s.lastIndexOf("."));
            try (FileInputStream fileInputStream = new FileInputStream(s);
                 FileOutputStream fileOutputStream = new FileOutputStream(path, true)) {
                byte[] bytes = new byte[fileInputStream.available()];
                while (fileInputStream.available() > 0) {
                    int bytesRead = fileInputStream.read(bytes);
                    fileOutputStream.write(bytes, 0, bytesRead);
                }
            }
        }
    }
}

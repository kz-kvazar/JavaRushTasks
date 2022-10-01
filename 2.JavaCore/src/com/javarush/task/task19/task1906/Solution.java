package com.javarush.task.task19.task1906;

import java.io.*;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        int count = 1;
        try (FileReader fileReader = new FileReader(file1);
             FileWriter fileWriter = new FileWriter(file2)) {
            while (fileReader.ready()) {
                int read = fileReader.read();
                if (count % 2 == 0) {
                    fileWriter.write(read);
                }
                count++;
            }
        }
    }
}

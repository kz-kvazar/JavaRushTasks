package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        try (FileWriter fileWriter = new FileWriter(file1);
             FileReader fileReader = new FileReader(file2);
             FileReader fileReader1 = new FileReader(file3)) {
            while (fileReader.ready()) {

                fileWriter.write(fileReader.read());
            }
            while (fileReader1.ready()) {
                fileWriter.append((char) fileReader1.read());
            }
        }
    }
}

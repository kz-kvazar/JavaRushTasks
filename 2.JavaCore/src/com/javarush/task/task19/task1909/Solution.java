package com.javarush.task.task19.task1909;

import java.io.*;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file1));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2))) {
            while (fileReader.ready()) {
                String readChar = fileReader.readLine();
                fileWriter.write(readChar.replaceAll("\\.", "!"));
            }
        }
    }
}

package com.javarush.task.task19.task1910;

import java.io.*;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFile = reader.readLine();
        String secondFile = reader.readLine();
        reader.close();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(firstFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(secondFile))) {
            while (bufferedReader.ready()) {
                bufferedWriter.write(bufferedReader.readLine().replaceAll("\\p{P}", ""));
            }
        }
    }
}

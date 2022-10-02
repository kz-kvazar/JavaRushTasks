package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            int wordCount = 0;
            while (fileReader.ready()) {

                String replacedString = fileReader.readLine().replaceAll("\\p{P}", " ").replaceAll("\\s", " ");
                String[] words = replacedString.split(" ");
                for (String s : words) {
                    if (s.trim().equals("world")) {
                        wordCount++;
                    }
                }
            }
            System.out.println(wordCount);
        }
    }
}

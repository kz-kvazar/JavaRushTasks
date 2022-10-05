package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            while (reader.ready()) {
                String[] lines = reader.readLine().split(" ");
                for (String s : lines) {
                    if (s.matches(".*[0-9].*")) {
                        stringBuilder.append(s).append(" ");
                    }
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            writer.write(stringBuilder.toString());
        }
    }
}

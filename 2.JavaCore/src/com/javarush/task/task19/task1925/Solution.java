package com.javarush.task.task19.task1925;

import java.io.*;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            while (reader.ready()) {
                String[] words = reader.readLine().split(" ");
                for (String s : words) {
                    if (s.length() > 6) {
                        stringBuilder.append(s).append(",");
                    }
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            writer.write(stringBuilder.toString());
        }
    }
}

package com.javarush.task.task19.task1908;

import java.io.*;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(consoleReader.readLine()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(consoleReader.readLine()))) {
            while (reader.ready()) {
                String[] read = reader.readLine().split(" ");
                for (String s : read) {
                    try{
                        Integer i = Integer.parseInt(s);
                        writer.write(i + " ");
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
    }
}

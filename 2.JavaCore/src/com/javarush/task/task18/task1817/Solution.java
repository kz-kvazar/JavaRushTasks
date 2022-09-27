package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int spaces = 0;
        int chars = 0;

        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                if (fileReader.read() == ' ') spaces++;
                chars++;
            }
        }
        double result = (double) spaces / chars * 100;
        System.out.printf("%.2f", result);
    }
}

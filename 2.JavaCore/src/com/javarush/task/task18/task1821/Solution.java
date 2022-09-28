package com.javarush.task.task18.task1821;

/*
Встречаемость символов
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int[] bytes = new int[128];
        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                bytes[fileReader.read()]++;
            }
        }
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != 0) System.out.println((char) i +" " + bytes[i]);
            }
        }
    }


package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (FileInputStream fis = new FileInputStream(reader.readLine())) {
            int[] bytes = new int[256];
            while (fis.available() > 0) {
                bytes[fis.read()] += 1;
            }
            int maxInt = 0;
            for (Integer i : bytes) {
                if (i > maxInt) maxInt = i;
            }
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] == maxInt) {
                    System.out.print(i + " ");
                }
            }
        }
    }
}

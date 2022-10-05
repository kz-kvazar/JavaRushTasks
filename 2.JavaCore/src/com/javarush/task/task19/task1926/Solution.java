package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName  = bufferedReader.readLine();
        bufferedReader.close();
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while (reader.ready()){
                result.append(reader.readLine());
                System.out.println(result.reverse());
                result.delete(0, result.length());
            }
        }

    }
}

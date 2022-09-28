package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName), 200)) {
            while (bufferedReader.ready()) {
                String read = bufferedReader.readLine();
                if (read.startsWith(args[0] + " ")) System.out.println(read);
            }
        }
    }
}

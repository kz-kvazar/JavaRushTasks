package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = "";
        while (true) {
            try (FileReader fileReader = new FileReader(path = reader.readLine())) {

            } catch (FileNotFoundException e) {
                System.out.println(path);
                break;
            }
        }
    }
}

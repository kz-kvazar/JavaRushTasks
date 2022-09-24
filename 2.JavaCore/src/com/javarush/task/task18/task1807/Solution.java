package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        try (FileInputStream fis = new FileInputStream(reader.readLine())){
            while (fis.available()>0){
                if (fis.read() == 44){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}

package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
        int result = Integer.MAX_VALUE;
        int read;
        bufferedReader.close();
        while (fileInputStream.available()>0){
            if((read = fileInputStream.read())<result) result = read;
        }
        fileInputStream.close();
        System.out.println(result);
    }
}

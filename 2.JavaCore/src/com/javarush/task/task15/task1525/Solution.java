package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<>();

    static {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Statics.FILE_NAME))){
            while (bufferedReader.ready()){
                lines.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println(lines);
    }
}

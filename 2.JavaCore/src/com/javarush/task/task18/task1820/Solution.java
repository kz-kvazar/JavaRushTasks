package com.javarush.task.task18.task1820;

import java.io.*;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String filename2 = reader.readLine();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
             PrintWriter fileWriter = new PrintWriter(new FileWriter(filename2))) {
            while (fileReader.ready()) {
                String[] strings = fileReader.readLine().split(" ");
                for (String s : strings) {
                    double number = Double.parseDouble(s);
                    long roundedNumber = Math.round(number);
                    fileWriter.print(roundedNumber + " ");
                }
            }
        }
    }
}

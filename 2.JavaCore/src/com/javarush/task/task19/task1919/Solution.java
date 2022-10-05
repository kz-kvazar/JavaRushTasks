package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        TreeMap<String, Double> map = new TreeMap<>();
        while (bufferedReader.ready()) {
            String[] lineArr = bufferedReader.readLine().split(" ");
            if (map.containsKey(lineArr[0])) {
                map.replace(lineArr[0], Double.parseDouble(lineArr[1]) + map.get(lineArr[0]));
            } else {
                map.put(lineArr[0], Double.valueOf(lineArr[1]));
            }
        }
        bufferedReader.close();
        for (Map.Entry<String, Double> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}

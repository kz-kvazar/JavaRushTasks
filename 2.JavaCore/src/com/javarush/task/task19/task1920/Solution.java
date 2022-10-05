package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<String, Double> valueMap = new TreeMap<>();
        if (args[0] != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                while (reader.ready()) {
                    String[] read = reader.readLine().split(" ");
                    if (!valueMap.containsKey(read[0])) {
                        valueMap.put(read[0], Double.parseDouble(read[1]));
                    } else {
                        valueMap.replace(read[0], Double.parseDouble(read[1]) + valueMap.get(read[0]));
                    }
                }
            }
            double maxSalary = valueMap.firstEntry().getValue();
            for (Map.Entry<String, Double> map : valueMap.entrySet()) {
                if (maxSalary < map.getValue()) {
                    maxSalary = map.getValue();
                }
            }
            for (Map.Entry<String, Double> map : valueMap.entrySet()) {
                if (maxSalary == map.getValue()) {
                    System.out.println(map.getKey());
                }
            }
        }
    }
}

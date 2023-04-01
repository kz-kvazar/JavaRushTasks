package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> set = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String s;
            while ((s = br.readLine()) != null) {
                for (char ch : s.toLowerCase().toCharArray()) {
                    if (ch >= 97 && ch <= 122)
                        set.add(ch);
                }
            }
        }
        int count = 0;
        for (Character c : set) {
            System.out.print(c);
            count++;
            if (count == 5) {
                break;
            }
        }
    }
}

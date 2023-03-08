package com.javarush.task.task30.task3010;

import com.sun.javafx.binding.StringFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
            String input = args[0];
            Matcher matcher = Pattern.compile("[^0-9A-Za-z]").matcher(input);
            if (matcher.find()) {
                System.out.println("incorrect");
            } else {
                int radix = 2;
                for (char c : input.toUpperCase().toCharArray()) {
                    if (Character.isDigit(c)) {
                        radix = Math.max(radix, c - '0' + 1);
                    } else {
                        radix = Math.max(radix, c - 'A' + 11);
                    }
                }
                System.out.println(radix);
            }
        } catch (Exception ignored) {
        }
    }
}
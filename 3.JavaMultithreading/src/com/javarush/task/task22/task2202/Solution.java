package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();
        String[] strings = string.split(" ");
        if (strings.length < 5) throw new TooShortStringException();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            result.append(strings[i]).append(" ");
        }
        return result.toString().trim();


    }

    public static class TooShortStringException extends RuntimeException {
    }
}

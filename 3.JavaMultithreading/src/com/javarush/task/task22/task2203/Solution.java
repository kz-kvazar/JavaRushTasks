package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }
        String[] strings = string.split("\t");
        if (strings.length > 2) {
            return strings[1];
        } else {
            throw new TooShortStringException();
        }
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }

    public static class TooShortStringException extends Exception {
    }
}

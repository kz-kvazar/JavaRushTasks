package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        testString.printSomething();
        System.setOut(defaultStream);
        String[] numbers = byteArrayOutputStream.toString().split("\\D+");
        int result = 0;
        String plus = byteArrayOutputStream.toString().replace(numbers[0], "").replace(numbers[1], "").replace("=", "").replaceAll("\\s", "");
        switch (plus) {
            case "+":
                result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                break;
            case "-":
                result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
                break;
            case "*":
                result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
                break;
        }
        System.out.println(byteArrayOutputStream.toString().replace("\r\n", "") + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}


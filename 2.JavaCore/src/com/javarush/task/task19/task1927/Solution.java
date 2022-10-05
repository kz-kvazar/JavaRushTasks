package com.javarush.task.task19.task1927;

import java.io.*;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        PrintStream defaultPrint = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        testString.printSomething();
        System.setOut(defaultPrint);
        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            defaultPrint.println(lines[i]);
            if (i % 2 != 0)
                System.out.println("JavaRush - курсы Java онлайн");
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}

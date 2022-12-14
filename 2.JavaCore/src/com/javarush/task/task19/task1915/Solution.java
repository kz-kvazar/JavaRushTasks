package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
//        BufferedReader url = new BufferedReader(new InputStreamReader( new URL("https://www.google.com/webhp?hl=ru&sa=X&ved=0ahUKEwiL15SQxsL6AhUI3qQKHY-NCUMQPAgI").openStream()));
//
//        while (url.ready()){
//            System.out.println(url.readLine());
//        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        PrintStream defaultStream = System.out;

        System.setOut(printStream);
        testString.printSomething();

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            System.setOut(defaultStream);
            System.out.println(byteArrayOutputStream);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}


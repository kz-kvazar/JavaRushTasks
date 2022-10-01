package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String[] read = fileScanner.nextLine().split(" ");
            Date birth = new SimpleDateFormat("ddMMyyyy").parse(read[3]+read[4]+read[5]);
            return new Person(read[1],read[2],read[0], birth);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}

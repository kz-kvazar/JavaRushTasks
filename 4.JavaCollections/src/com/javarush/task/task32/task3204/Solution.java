package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/*
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean containsDigit = false;
        boolean containsLowercase = false;
        boolean containsUppercase = false;

        while (!(containsDigit && containsLowercase && containsUppercase)) {
            baos.reset();
            for (int i = 0; i < 8; i++) {
                int rand = (int) (Math.random() * 3);
                switch (rand) {
                    case 0:
                        baos.write((char) ((Math.random() * 26) + 97)); // lowercase letters
                        containsLowercase = true;
                        break;
                    case 1:
                        baos.write((char) ((Math.random() * 26) + 65)); // uppercase letters
                        containsUppercase = true;
                        break;
                    case 2:
                        baos.write((char) ((Math.random() * 10) + 48)); // digits
                        containsDigit = true;
                        break;
                }
            }
        }

        return baos;
    }
}

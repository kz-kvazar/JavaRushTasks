package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    public static String generateRandomString(){
        int length = 10; // Длина случайной строки
        SecureRandom secureRandom = new SecureRandom();
        BigInteger bigInteger = new BigInteger(130, secureRandom);
        String randomString = bigInteger.toString(32).substring(0, length);

        return randomString;
    }
    public static void printMessage(String message){
        System.out.println(message);
    }
}

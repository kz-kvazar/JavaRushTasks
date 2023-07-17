package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString(){
        try {
            return bis.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
    public static String askCurrencyCode(){
        writeMessage("Введите код валюты");
        String result = "";
        while (true){
            result = readString();
            if (result.matches("[a-zA-Z]+") && result.length() == 3){
                return result.toUpperCase();
            }else writeMessage("Некорректный код валюты.");
        }
    }
    public static String[] getValidTwoDigits(String currencyCode){
        writeMessage(String.format("Please specify integer denomination and integer count. For example '10 3' means 30 %s", currencyCode));
        String result = "";
        while (true){
            result = readString();
            String[] stringsResult = result.split(" ");
            try{
            Integer denomination = Integer.parseInt(stringsResult[0]);
            Integer count = Integer.parseInt(stringsResult[1]);
                if (denomination > 0 && count >0 && stringsResult.length == 2){
                    return stringsResult;
                }else writeMessage("Неверные данные.");
            }catch (Exception e){
                writeMessage("Неверные данные.");
            }
        }
    }
}

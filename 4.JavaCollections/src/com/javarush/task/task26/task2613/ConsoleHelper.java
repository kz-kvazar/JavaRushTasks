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
            int denomination = Integer.parseInt(stringsResult[0]);
            int count = Integer.parseInt(stringsResult[1]);
                if (denomination > 0 && count >0 && stringsResult.length == 2){
                    return stringsResult;
                }else writeMessage("Неверные данные.");
            }catch (Exception e){
                writeMessage("Неверные данные.");
            }
        }
    }
    public static Operation askOperation(){

        while (true){
            ConsoleHelper.writeMessage("Please choose an operation desired or type 'EXIT' for exiting");
            ConsoleHelper.writeMessage("\t 1 - operation.INFO");
            ConsoleHelper.writeMessage("\t 2 - operation.DEPOSIT");
            ConsoleHelper.writeMessage("\t 3 - operation.WITHDRAW");
            ConsoleHelper.writeMessage("\t 4 - operation.EXIT");
            Integer i = Integer.parseInt(ConsoleHelper.readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                writeMessage("Не верно выбранная операция");
            }
        }
    }

}

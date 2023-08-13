package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + "resources.common");
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String text = bis.readLine();
            if ("exit".equals(text.toLowerCase())) {
                throw new InterruptOperationException();
            }

            return text;
        } catch (IOException ignored) { //suppose it will never occur
        }
        return "";
    }
    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String result = "";
        while (true){
            result = readString();
            if (result.matches("[a-zA-Z]+") && result.length() == 3){
                return result.toUpperCase();
            }else writeMessage(res.getString("invalid.data"));
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String result = "";
        while (true){
            result = readString();
            String[] stringsResult = result.split(" ");
            try{
            int denomination = Integer.parseInt(stringsResult[0]);
            int count = Integer.parseInt(stringsResult[1]);
                if (denomination > 0 && count >0 && stringsResult.length == 2){
                    return stringsResult;
                }else writeMessage(res.getString("invalid.data"));
            }catch (Exception e){
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
    public static Operation askOperation() throws InterruptOperationException {

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
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

}

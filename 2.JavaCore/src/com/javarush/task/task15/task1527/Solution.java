package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();

        StringBuilder result = new StringBuilder();
        String[] listEquals;
        String[] list;
        String obj = null;
        url = url.substring(url.indexOf("?") + 1);
        listEquals = url.split("&");
        for (String s : listEquals) {
            list = s.split("=");
            result.append(list[0]);
            result.append(" ");
            if (s.contains("obj")) {
                obj = list[1];
            }
        }
        System.out.println(result);
        if (obj != null) {
            try {
                alert(Double.parseDouble(obj));
            } catch (NumberFormatException e) {
                alert(obj);
            }
        }
    }


    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}

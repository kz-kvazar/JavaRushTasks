package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("name", "Ivanov");
        paramsMap.put("country", "Ukraine");
        paramsMap.put("city", "Kiev");
        paramsMap.put("age", null);

        System.out.println(getQuery(paramsMap));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (e.getValue() != null && e.getKey() != null) {
                sb.append(e.getKey()).append(" = '").append(e.getValue()).append("' and ");
            }
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - 5, sb.length());
        }
        return sb.toString();
    }
}

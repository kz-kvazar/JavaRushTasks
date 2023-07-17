package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static final Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator currencyManipulator = map.get(currencyCode.toUpperCase());
        if (currencyManipulator == null) {
            currencyManipulator = new CurrencyManipulator(currencyCode.toUpperCase());
            map.put(currencyCode.toUpperCase(), currencyManipulator);
        }
        return currencyManipulator;
    }
}

package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private final String currencyCode;
    private final Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public boolean hasMoney(){
        return !denominations.isEmpty();
    }
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            Integer balans = denominations.get(denomination);
            denominations.put(denomination, balans + count);
        } else {
            denominations.put(denomination, count);
        }
    }
    public int getTotalAmount(){
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            amount += entry.getKey() * entry.getValue();
        }
        return amount;
    }
}

package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command {

    @Override
    public void execute() {
        Collection<CurrencyManipulator> allCurrencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (allCurrencyManipulators.isEmpty()) {
            ConsoleHelper.writeMessage("No money available.");
        }
        for (CurrencyManipulator currency : allCurrencyManipulators) {
            if (currency.hasMoney()) {
                String currencyCode = currency.getCurrencyCode();
                int totalAmount = currency.getTotalAmount();
                ConsoleHelper.writeMessage(currencyCode + " - " + totalAmount);
            }
        }
    }
}

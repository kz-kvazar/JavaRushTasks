package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private final String name;

    private boolean busy;

    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startCookingOrder(Order order) {
        
        this.busy = true;

        Tablet tablet = order.getTablet();

        ConsoleHelper.writeMessage(name + " Start cooking - " + order);

        int totalCookingTime = order.getTotalCookingTime();
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, totalCookingTime * 60, order.getDishes());
        StatisticManager.getInstance().register(row);

        try {
            Thread.sleep(totalCookingTime * 10);
        } catch (InterruptedException ignored) {
        }

        setChanged();
        notifyObservers(order);
        this.busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
       // Set<Cook> cooks = StatisticManager.getInstance().getCooks();
        try {
            while (true) {
                Thread.sleep(10);
                if (!queue.isEmpty()) {
                        if (!isBusy()) {
                            startCookingOrder(queue.take());
                        }
                }
            }
        } catch (InterruptedException e) {
        }
    }
}

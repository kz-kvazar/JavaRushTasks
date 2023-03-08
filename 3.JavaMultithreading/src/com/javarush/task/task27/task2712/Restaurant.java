package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {

        Cook cook2 = new Cook("NEO");
        Cook cook = new Cook("Amigo");

        StatisticManager.getInstance().register(cook);
        StatisticManager.getInstance().register(cook2);

        OrderManager orderManager = new OrderManager();
        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet tablet = new Tablet(i);
            tabletList.add(tablet);
            tablet.addObserver(orderManager);
        }

        Waiter waiter = new Waiter();

        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generatorTask);
        thread.setDaemon(true);
        thread.start();


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();


    }
}

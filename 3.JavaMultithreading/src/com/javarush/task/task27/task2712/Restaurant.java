package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>(200);

    public static void main(String[] args) {

        Cook cook2 = new Cook("NEO");
        Cook cook = new Cook("Amigo");

        cook.setQueue(ORDER_QUEUE);
        cook2.setQueue(ORDER_QUEUE);

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet tablet = new Tablet(i);
            tabletList.add(tablet);
            tablet.setQueue(ORDER_QUEUE);
            //tablet.addObserver(orderManager);
        }

        Waiter waiter = new Waiter();

        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread cook11 = new Thread(cook);
        cook11.start();
        Thread cook22 = new Thread(cook2);
        cook22.start();

        Thread threadGenerator = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        threadGenerator.setDaemon(true);
        threadGenerator.start();


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();


    }
}

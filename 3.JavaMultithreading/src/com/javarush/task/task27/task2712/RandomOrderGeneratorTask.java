package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tabletList;
    private int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tabletList, int ORDER_CREATING_INTERVAL) {
        this.ORDER_CREATING_INTERVAL = ORDER_CREATING_INTERVAL;
        this.tabletList = tabletList;
    }

    @Override
    public void run() {
        
        while (true) {
            try {
                int randomInt = (int) (Math.random() * tabletList.size());
                Tablet randomTablet = tabletList.get(randomInt);
                randomTablet.createTestOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (Exception e) {
            }
        }
    }
}

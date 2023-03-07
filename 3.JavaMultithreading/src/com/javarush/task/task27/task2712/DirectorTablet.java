package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Map<String, Long> reportMap = StatisticManager.getInstance().calculateDailyAdRevenue();
        double total = 0d;
        for (Map.Entry<String, Long> e : reportMap.entrySet()) {
            double value = 1.0 * e.getValue() / 100;
            ConsoleHelper.writeMessage(e.getKey() + " - " + String.format(Locale.ENGLISH, "%.2f", value));
            total += value;
        }
        ConsoleHelper.writeMessage("Total - " + String.format(Locale.ENGLISH, "%.2f", total));
    }

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> reportMap = StatisticManager.getInstance().calculateDailyCookWorkloading();
        for (Map.Entry<String, Map<String, Integer>> e : reportMap.entrySet()) {
            ConsoleHelper.writeMessage(e.getKey());
            Map<String, Integer> cooks = e.getValue();
            for (Map.Entry<String, Integer> c : cooks.entrySet()) {
                ConsoleHelper.writeMessage(c.getKey() + " - " + (c.getValue() + 59) / 60 + " min");
            }
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> activeAdvertisement = StatisticAdvertisementManager.getInstance().getActiveAdvertisement();
        activeAdvertisement.sort(Comparator.comparing(ad -> ad.getName().toLowerCase()));
        for (Advertisement a : activeAdvertisement) {
            ConsoleHelper.writeMessage(a.getName() + " - " + a.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> notActiveAdvertisement = StatisticAdvertisementManager.getInstance().getNotActiveAdvertisement();
       notActiveAdvertisement.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        for (Advertisement a : notActiveAdvertisement) {
            ConsoleHelper.writeMessage(a.getName());
        }
    }

}

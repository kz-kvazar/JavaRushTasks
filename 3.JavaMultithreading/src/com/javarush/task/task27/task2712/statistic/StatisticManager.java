package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private StatisticStorage statisticStorage = new StatisticStorage();
    private StatisticManager() {
    }

    private static class InstanceHolder {
        public static StatisticManager statisticManager = new StatisticManager();
    }

    public static StatisticManager getInstance() {
        return InstanceHolder.statisticManager;
    }

    public void register(EventDataRow data) {

    }

    private class StatisticStorage {
        private Map <EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            this.storage = new HashMap<>();
            for (EventType e : EventType.values()) {
                storage.put(e,new ArrayList<EventDataRow>());
            }
        }
    }
}

package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            this.storage.get(type).add(data);
        }
    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    public void register(Cook cook){
        this.cooks.add(cook);
    }
    public Map<String,Long> calculateDailyAdRevenue(){
        List<EventDataRow> eventDataRows = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String,Long> report = new HashMap<>();

        for (EventDataRow e: eventDataRows){
            VideoSelectedEventDataRow videoSelectedEvent = (VideoSelectedEventDataRow) e;
            String eventDate = sdf.format(videoSelectedEvent.getDate());
            if (report.containsKey(eventDate)){
                report.put(eventDate,report.get(eventDate) + videoSelectedEvent.getAmount());
            } else {
                report.put(eventDate,videoSelectedEvent.getAmount());            }
        }
        return report;
    }
    public Map<String,Map<String,Integer>> calculateDailyCookWorkloading(){
        List<EventDataRow> eventDataRows = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String,Map<String,Integer>> report = new HashMap<>();

        for (EventDataRow e : eventDataRows) {
            CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) e;
            String eventDate = sdf.format(cookedOrderEvent.getDate());
            if (report.containsKey(eventDate)){
                if (report.get(eventDate).containsKey(cookedOrderEvent.getCookName())){
                    report.get(eventDate).put(cookedOrderEvent.getCookName(),cookedOrderEvent.getTime() + report.get(eventDate).get(cookedOrderEvent.getCookName()));
                }else {
                    report.get(eventDate).put(cookedOrderEvent.getCookName(),cookedOrderEvent.getTime());
                }
            } else {
                Map<String, Integer> cookMap = new HashMap<>();
                cookMap.put(cookedOrderEvent.getCookName(), cookedOrderEvent.getTime());
                report.put(eventDate, cookMap);            }
        }
        return report;
    }
}

package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

public class StatisticManager {
    private StatisticManager() {
    }
    private static class InstanceHolder{
        public static StatisticManager statisticManager = new StatisticManager();
    }
    public static StatisticManager getInstance(){
        return InstanceHolder.statisticManager;
    }
    public void register(EventDataRow data){

    }
}

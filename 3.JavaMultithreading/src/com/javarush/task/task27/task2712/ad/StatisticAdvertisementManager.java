package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatisticAdvertisementManager {
    private static class InstanceHolder{
        public static StatisticAdvertisementManager advertisementManager = new StatisticAdvertisementManager();
    }
    public static StatisticAdvertisementManager getInstance(){
        return InstanceHolder.advertisementManager;
    }

    private StatisticAdvertisementManager() {
    }

    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();
    public List<Advertisement> getActiveAdvertisement(){
        List<Advertisement> activeAdvertisementList = new ArrayList<>();
        for (Advertisement a : advertisementStorage.list()) {
            if (a.isActive()){
                activeAdvertisementList.add(a);
            }
        }
        return activeAdvertisementList;
    }
    public List<Advertisement> getNotActiveAdvertisement(){
        List<Advertisement> NotActiveAdvertisementList = new ArrayList<>();
        for (Advertisement a : advertisementStorage.list()) {
            if (!a.isActive()){
                NotActiveAdvertisementList.add(a);
            }
        }
        return NotActiveAdvertisementList;
    }
}

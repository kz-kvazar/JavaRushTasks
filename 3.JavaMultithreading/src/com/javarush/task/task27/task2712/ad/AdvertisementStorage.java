package com.javarush.task.task27.task2712.ad;

public class AdvertisementStorage {
    private static class InstanceHolder{
        private static final AdvertisementStorage storage = new AdvertisementStorage();
    }

    private AdvertisementStorage() {
    }
    
    public static AdvertisementStorage getInstance(){
        return InstanceHolder.storage;
    }
}

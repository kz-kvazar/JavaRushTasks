package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        this.totalTimeSecondsLeft = Integer.MAX_VALUE;
        obtainOptimalVideoSet(new HashSet<>(), timeSeconds, 0L);

        VideoSelectedEventDataRow row = new VideoSelectedEventDataRow(optimalVideoSet, maxAmount, timeSeconds - totalTimeSecondsLeft);
        StatisticManager.getInstance().register(row);

        displayAdvertisement();
    }


    //recursion
    private long maxAmount;
    private List<Advertisement> optimalVideoSet;
    private int totalTimeSecondsLeft;

    private void obtainOptimalVideoSet(Set<Advertisement> totalSet, int currentTimeSecondsLeft, long currentAmount) {
        if (currentAmount > maxAmount
                || currentAmount == maxAmount && (totalTimeSecondsLeft > currentTimeSecondsLeft
                || totalTimeSecondsLeft == currentTimeSecondsLeft && totalSet.size() < optimalVideoSet.size())) {
            this.totalTimeSecondsLeft = currentTimeSecondsLeft;
            this.optimalVideoSet = new ArrayList<>(totalSet);
            this.maxAmount = currentAmount;
        }

        if (currentTimeSecondsLeft == 0) {
            return;
        }

        List<Advertisement> actualAdvertisements = getActualAdvertisements();
        for (Advertisement ad : actualAdvertisements) {
            if (!ad.isActive() || totalSet.contains(ad) || ad.getDuration() > currentTimeSecondsLeft) {
                continue;
            }
            Set<Advertisement> currentSet = new HashSet<>(totalSet);
            currentSet.add(ad);
            obtainOptimalVideoSet(currentSet, currentTimeSecondsLeft - ad.getDuration(), currentAmount + ad.getAmountPerOneDisplaying());
        }
    }

    private List<Advertisement> getActualAdvertisements() {
        return storage.list().stream()
                .filter(Advertisement::isActive)
                .collect(Collectors.toList());
    }

    private void displayAdvertisement() {
        if (optimalVideoSet == null || optimalVideoSet.isEmpty()) {
            //StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(totalTimeSecondsLeft));
            throw new NoVideoAvailableException();
        }

        optimalVideoSet.sort(Comparator.comparing(Advertisement::getAmountPerOneDisplaying).reversed()
                .thenComparing(Advertisement::getDuration));



        StringBuilder builder = new StringBuilder();
        for (Advertisement ad : optimalVideoSet) {
            builder.append(ad.getName())
                    .append(" is displaying... ")
                    .append(ad.getAmountPerOneDisplaying())
                    .append(", ")
                    .append(1000 * ad.getAmountPerOneDisplaying() / ad.getDuration())
                    .append("\n");
            ad.revalidate();
        }
        System.out.println(builder.toString());
    }

    public static class StatisticAdvertisementManager {
        private static class InstanceHolder {
            static StatisticAdvertisementManager advertisementManager = new StatisticAdvertisementManager();
        }

        public static StatisticAdvertisementManager getInstance() {
            return InstanceHolder.advertisementManager;
        }

        AdvertisementStorage storage = AdvertisementStorage.getInstance();

        public void getActiveAdvertisements(){
            storage.list();
        }
    }
}

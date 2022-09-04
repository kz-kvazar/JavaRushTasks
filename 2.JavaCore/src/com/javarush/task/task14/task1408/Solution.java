package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        //System.out.println(String.format("Я - курица. Моя страна - %s . Я несу %s яиц в месяц.",Country.BELARUS,0));
        Hen hen = HenFactory.getHen(Country.BELARUS);
        //hen.getCountOfEggsPerMonth();
        System.out.println(hen.getDescription());
    }

    static class HenFactory {

        static Hen getHen(String country) {
            switch (country) {
                case Country.RUSSIA: {
                    return new RussianHen();
                }
                case Country.BELARUS: {
                    return new BelarusianHen();
                }
                case Country.MOLDOVA: {
                    return new MoldovanHen();
                }
                case Country.UKRAINE: {
                    return new UkrainianHen();
                }
                default: {
                    return null;
                }
            }

        }
    }
}

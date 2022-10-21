package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    private List<Horse> horses;

    static Hippodrome game;
    public static void main(String[] args) {
        List<Horse> horse = new ArrayList<>();
        horse.add(new Horse("Степан", 3,0));
        horse.add(new Horse("Молния", 3,0));
        horse.add(new Horse("Плотва", 3,0));
        game = new Hippodrome(horse);
    }
}

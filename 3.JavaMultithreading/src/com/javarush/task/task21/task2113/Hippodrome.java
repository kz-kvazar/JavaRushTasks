package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public List<Horse> getHorses() {
        return horses;
    }

    public Horse getWinner(){
        double distance = 0d;
        Horse winner = null;
        for (Horse h : horses) {
            if (h.getDistance() > distance) {
                distance = h.getDistance();
                winner = h;
            }
        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    public void  run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move(){
        for (Horse h : horses) {
            h.move();
        }
    }
    public void print(){
        for (Horse h : horses) {
            h.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    
    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    private List<Horse> horses;

    static Hippodrome game;
    public static void main(String[] args) throws InterruptedException {
        List<Horse> horse = new ArrayList<>();
        horse.add(new Horse("Степан", 3,0));
        horse.add(new Horse("Молния", 3,0));
        horse.add(new Horse("Плотва", 3,0));
        game = new Hippodrome(horse);
        game.run();
        game.printWinner();

    }
}
